package com.zavadski.service.api;

import com.zavadski.model.Payment;
import com.zavadski.model.dto.CreatePaymentDto;

import java.util.List;

public interface PaymentService {

    List<Payment> findAllPayments();

    Payment createPayment(CreatePaymentDto createPaymentDto) throws Exception;

}
