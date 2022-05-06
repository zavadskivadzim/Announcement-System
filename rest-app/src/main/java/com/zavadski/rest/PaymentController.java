package com.zavadski.rest;

import com.zavadski.model.Payment;
import com.zavadski.model.dto.CreatePaymentDto;
import com.zavadski.service.api.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    private static final Logger logger = LogManager.getLogger(PaymentController.class);

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/admin/payments")
    public final List<Payment> findAllPayments() {

        logger.info("find All Payments");

        return paymentService.findAllPayments();
    }

    @PostMapping(path = "/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public final Payment createPayment(@RequestBody CreatePaymentDto createPaymentDto) throws Exception {

        logger.info("create Payment ({})", createPaymentDto);

        return paymentService.createPayment(createPaymentDto);
    }

}
