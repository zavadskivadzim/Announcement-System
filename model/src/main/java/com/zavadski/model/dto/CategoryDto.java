package com.zavadski.model.dto;

import com.zavadski.model.Category;
import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {

    private UUID id = UUID.randomUUID();
    private String name;

    public Category toCategory() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    public static CategoryDto fromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}