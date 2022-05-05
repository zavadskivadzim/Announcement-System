package com.zavadski.service;

import com.zavadski.dao.api.PaymentDao;
import com.zavadski.model.Payment;
import com.zavadski.model.User;
import com.zavadski.service.api.PaymentService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final UserService userService;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao, UserService userService) {
        this.paymentDao = paymentDao;
        this.userService = userService;
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentDao.findAll();
    }

    @Override
    public Payment findPaymentById(UUID id) {
        return paymentDao.findById(id);
    }

    @Override
    public Payment createPayment(Payment payment) {
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        payment.setAnnouncement(payment.getAnnouncement());
        payment.setPaidFrom(LocalDateTime.now());
        payment.setPaidTo(payment.getPaidTo());
        return paymentDao.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) throws Exception{

        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(payment.getAnnouncement().getUser())) {
            payment.setPaidTo(payment.getPaidTo());
            return paymentDao.update(payment);
        } else {
            throw new Exception("you can't delete this payment");
        }
    }


    @Override
    public void deletePayment(UUID id) throws Exception {

        Payment payment = findPaymentById(id);
        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(payment.getAnnouncement().getUser())) {
            paymentDao.delete(id);
        } else {
            throw new Exception("you can't delete this payment");
        }
    }

}
