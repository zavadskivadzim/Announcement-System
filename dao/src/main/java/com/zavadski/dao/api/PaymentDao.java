package com.zavadski.dao.api;

import com.zavadski.model.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentDao {

    List<Payment> findAll();

    Payment findById(UUID id);

    Payment save(Payment payment);

    Payment update(Payment payment);

    void delete(UUID id);

}
