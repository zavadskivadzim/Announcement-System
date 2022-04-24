package com.zavadski.dao;

import com.zavadski.dao.api.CategoryDao;
import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.dao.util.HibernateUtil;
import com.zavadski.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.zavadski.model.constants.Constants.CATEGORY_NAME_SIZE;

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

        if (category.getName().length() > CATEGORY_NAME_SIZE) {
            logger.warn("Category name is too long {}", category.getName().length());
            throw new UnacceptableName("Category name must be no more than "
                    + CATEGORY_NAME_SIZE + " characters long");
        }

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

        if (category.getName().length() > CATEGORY_NAME_SIZE) {
            logger.warn("Category name is too long {}", category.getName().length());
            throw new UnacceptableName("Category name must be no more than "
                    + CATEGORY_NAME_SIZE + " characters long");
        }

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

}
