package com.zavadski.rest;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Category;
import com.zavadski.model.dto.CategoryDto;
import com.zavadski.service.api.CategoryService;
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
    public final List<CategoryDto> findAllCategory() {

        logger.info("find All Categories");

        return categoryService.findAllCategories()
                .stream().map(CategoryDto::fromCategory).collect(Collectors.toList());
    }

    @GetMapping(value = "/categories/{id}")
    public final CategoryDto getCategoryById(@PathVariable String id) {

        logger.info("get Category By Id={}", id);

        return CategoryDto.fromCategory(categoryService.findCategoryById(UUID.fromString(id)));
    }

    @PostMapping(path = "/admin/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public final Category createCategory(@RequestBody @Valid CategoryDto category, BindingResult result) {

        logger.info("create Category ({})", category);

        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            return categoryService.createCategory(category.toCategory());
        }
    }

    @PutMapping(value = "/admin/categories")
    @ResponseStatus(HttpStatus.OK)
    public final Category updateCategory(@RequestBody CategoryDto category) {

        logger.info("update Category {}", category);

        return categoryService.updateCategory(category.toCategory());
    }

    @DeleteMapping(value = "/admin/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable UUID id) {

        logger.info("delete Category by id={}", id);

        categoryService.deleteCategory(id);
    }
}
