package com.zavadski.dao;

import com.zavadski.dao.api.RoleDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

        logger.info("Create role {}", role);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();

        return role;
    }

    @Override
    public Role findById(UUID id) {

        logger.info("Find role by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.getTransaction().commit();
        session.close();

        return role;
    }

    @Override
    public Role update(Role role) {

        logger.info("Update role {}", role);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(role);
        session.getTransaction().commit();
        session.close();

        return role;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete role by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.delete(role);
        session.getTransaction().commit();
        session.close();
    }
}
