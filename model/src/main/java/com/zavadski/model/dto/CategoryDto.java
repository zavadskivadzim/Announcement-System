package com.zavadski.model.dto;

import com.zavadski.model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class CategoryDto {

    private UUID id;
    @NotBlank(message = "Category name can't be empty")
    @Size(max = 50, message = "Category name can't be more then {max} characters long")
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
