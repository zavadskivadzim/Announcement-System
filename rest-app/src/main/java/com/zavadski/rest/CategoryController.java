package com.zavadski.rest;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Category;
import com.zavadski.model.dto.CategoryDto;
import com.zavadski.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories")
    public final List<CategoryDto> findAllCategories() {

        logger.info("find All Categories");

        return categoryService.findAllCategories()
                .stream().map(CategoryDto::fromCategory).collect(Collectors.toList());
    }

    @PostMapping(path = "/admin/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public final String createCategory(@RequestBody @Valid CategoryDto category, BindingResult result) {

        logger.info("create Category ({})", category);
        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            if (!this.categoryService.checkCategoryOnUnique(category.getName())) {
                logger.warn("Category with name " + category.getName() + " already exists.");
                throw new UnacceptableName("Category with name " + category.getName() + " already exists.");
            } else {
                categoryService.createCategory(category.toCategory());
                logger.info("Category {} created" + category.getName());
                return "Category " + category.getName() + " created";
            }
        }
    }

    @PutMapping(value = "/admin/categories")
    public final String updateCategory(@RequestBody @Valid CategoryDto category, BindingResult result) {

        logger.info("update Category {}", category);
        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            Category updatedCategory = new Category();
            updatedCategory.setName(categoryService.findCategoryById(category.getId()).getName());
            if (this.categoryService.checkCategoryOnUnique(category.getName())
                    || (Objects.equals(category.getName(), updatedCategory.getName()))) {
                categoryService.updateCategory(category.toCategory());
                logger.info("Category {} updated to {}", updatedCategory.getName(), category.getName());
                return "Category " + updatedCategory.getName() + " updated to " + category.getName();
            } else {
                throw new UnacceptableName("Category " + category.getName() + " already exists.");
            }
        }
    }

    @DeleteMapping(value = "/admin/categories/{id}")
    public void deleteCategory(@PathVariable UUID id) {

        logger.info("delete Category by id={}", id);

        categoryService.deleteCategory(id);
    }
}
