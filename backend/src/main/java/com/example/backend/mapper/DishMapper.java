package com.example.backend.mapper;

import com.example.backend.dto.DishRequestDTO;
import com.example.backend.dto.DishResponseDTO;
import com.example.backend.entities.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
    public DishResponseDTO toResponseDTO(Dish dish) {
        if (dish == null) return null;

        return new DishResponseDTO(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getImageUrl(),
                dish.getCategory().getName(),
                dish.getAvailableStock(),
                dish.getIsActive()
        );
    }
    public DishRequestDTO toRequestDTO(Dish dish) {
        if (dish == null) return null;

        return new DishRequestDTO(
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getImageUrl(),
                dish.getCategory() != null ? dish.getCategory().getId() : null,
                dish.getAvailableStock(),
                dish.getIsActive()
        );
    }
}
