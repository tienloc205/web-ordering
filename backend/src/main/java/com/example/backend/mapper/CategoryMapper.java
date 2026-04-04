package com.example.backend.mapper;

import com.example.backend.dto.CategoryResponseDTO;
import com.example.backend.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDTO toResponseDTO(Category category) {
        if (category == null) return null;

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}
