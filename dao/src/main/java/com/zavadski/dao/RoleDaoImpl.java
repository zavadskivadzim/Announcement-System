package com.zavadski.dao;

import com.zavadski.dao.api.RoleDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
        List<Role> roleEntities = session.createQuery("from Role", Role.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return roleEntities;
    }

    @Override
    public Role findByName(String name) {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "from Role where name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        Role role = (Role) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return role;
    }

}