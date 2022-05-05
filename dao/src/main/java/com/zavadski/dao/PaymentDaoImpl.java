package com.zavadski.dao;

import com.zavadski.dao.api.PaymentDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private final Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

    @Override
    public List<Payment> findAll() {

        logger.info("Get all Payments");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Payment> payments = session.createQuery("from Payment", Payment.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return payments;
    }

    @Override
    public Payment findById(UUID id) {

        logger.info("Find Payment by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Payment payment = session.get(Payment.class, id);
        session.getTransaction().commit();
        session.close();

        return payment;
    }

    @Override
    public Payment save(Payment payment) {

        logger.info("Create Payment {}", payment);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(payment);
        session.getTransaction().commit();
        session.close();

        return payment;
    }

    @Override
    public Payment update(Payment payment) {

        logger.info("Update Payment {}", payment);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(payment);
        session.getTransaction().commit();
        session.close();

        return payment;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete Payment by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Payment payment = session.get(Payment.class, id);
        session.delete(payment);
        session.getTransaction().commit();
        session.close();
    }

}
