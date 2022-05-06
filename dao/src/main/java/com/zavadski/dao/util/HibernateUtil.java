package com.zavadski.dao.util;

import com.zavadski.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Announcement.class)
                .addAnnotatedClass(Grade.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Message.class)
                .buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
