package com.zavadski.service;

import com.zavadski.dao.api.PaymentDao;
import com.zavadski.model.Payment;
import com.zavadski.model.dto.CreatePaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;
    private final UserService userService;
    private final AnnouncementService announcementService;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao, UserService userService, AnnouncementService announcementService) {
        this.paymentDao = paymentDao;
        this.userService = userService;
        this.announcementService = announcementService;
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentDao.findAll();
    }

    @Override
    public Payment createPayment(CreatePaymentDto createPaymentDto) throws Exception {

        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(announcementService.findAnnouncementById(createPaymentDto.getAnnouncementId()).getUser())) {
            Payment payment = new Payment();
            payment.setAnnouncement(announcementService.findAnnouncementById(createPaymentDto.getAnnouncementId()));
            payment.setPaidFrom(LocalDateTime.now());
            payment.setPaidTo(LocalDateTime.now().plus(Duration.ofDays(createPaymentDto.getDuration())));
            return paymentDao.save(payment);
        } else {
            throw new Exception("you can't make this payment");
        }
    }

}
