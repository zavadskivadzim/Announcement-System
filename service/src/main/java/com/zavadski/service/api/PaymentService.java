package com.zavadski.service.api;

import com.zavadski.model.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    List<Payment> findAllPayments();

    Payment findPaymentById(UUID id);

    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment) throws Exception;

    void deletePayment(UUID id) throws Exception;

}
