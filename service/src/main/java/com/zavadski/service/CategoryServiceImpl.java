package com.zavadski.service;

import com.zavadski.dao.api.CategoryDao;
import com.zavadski.model.Category;
import com.zavadski.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category findById(UUID id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void delete(UUID id) {
        categoryDao.delete(id);
    }

}
