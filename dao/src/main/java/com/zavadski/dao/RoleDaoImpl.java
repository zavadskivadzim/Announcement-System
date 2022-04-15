package com.zavadski.dao;

import com.zavadski.dao.api.RoleDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Override
    public List<Role> findAll() {

        logger.info("Get all roles");


        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Role> roles = session.createQuery("from Role", Role.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return roles;
    }

    @Override
    public Role save(Role role) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Role.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();

        return role;
    }
}
