package com.zavadski.rest;

import com.zavadski.model.Category;
import com.zavadski.model.dto.CategoryDto;
import com.zavadski.service.api.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public final List<CategoryDto> findAllCategory() {

        logger.info("find All Categories");

        return categoryService.findAll()
                .stream().map(CategoryDto::fromCategory).collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/{id}")
    public final CategoryDto getCategoryById(@PathVariable UUID id) {

        logger.info("get Category By Id={}", id);

        Category category = categoryService.findById(id);
        return CategoryDto.fromCategory(category);
    }

    @PostMapping(path = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createCategory(@RequestBody CategoryDto category) {

        logger.info("create Category ({})", category);

        categoryService.save(category.toCategory());
    }

    @PutMapping(value = "/categories")
    @ResponseStatus(HttpStatus.OK)
    public final Category updateCategory(@RequestBody CategoryDto category) {

        logger.info("update Category {}", category);

        return categoryService.update(category.toCategory());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable UUID id) {

        logger.info("delete Category by id={}", id);

        categoryService.delete(id);
    }
}
