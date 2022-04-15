package com.zavadski.dao.util;

import com.zavadski.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
