package com.zavadski.dao;

import com.zavadski.dao.api.CategoryDao;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    @Override
    public List<Category> findAll() {

        logger.info("Get all categories");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return categories;
    }

    @Override
    public Category save(Category category) {

        logger.info("Create category {}", category);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();

        return category;
    }

    @Override
    public Category findById(UUID id) {

        logger.info("Find category by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);
        session.getTransaction().commit();
        session.close();

        return category;
    }

    @Override
    public Category update(Category category) {

        logger.info("Update category {}", category);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        session.close();

        return category;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Delete category by id={}", id);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean checkCategoryOnUnique(String categoryName) {

        logger.info("Check categoryName {} on unique", categoryName);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "select count(name) from Category where name = :categoryName";
        Query query = session.createQuery(hql);
        query.setParameter("categoryName", categoryName);
        Long result = (Long) query.getResultList().get(0);
        session.getTransaction().commit();
        session.close();

        return result == 0;
    }

}
