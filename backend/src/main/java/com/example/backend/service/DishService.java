package com.example.backend.service;

import com.example.backend.dto.DishRequestDTO;
import com.example.backend.dto.DishResponseDTO;
import com.example.backend.entities.Category;
import com.example.backend.entities.Dish;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.DishRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.backend.mapper.DishMapper;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;
    private final DishMapper dishMapper;


    //get all dishes
    public List<DishResponseDTO> getAllDishes() {
        return dishRepository.getAllDishes();
    }


    //get menu of dishes
    public Map<String, List<DishResponseDTO>> getMenu() {
        List<DishResponseDTO> allDishes = getAllDishes();

        return allDishes.stream()
                .collect(Collectors.groupingBy(
                        DishResponseDTO::getCategoryName,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }


    //create a new dish
    @Transactional
    public DishResponseDTO createDish(DishRequestDTO request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại"));

        Dish dish = new Dish();
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setPrice(request.getPrice());
        dish.setImageUrl(request.getImageUrl());
        dish.setCategory(category);
        dish.setAvailableStock(request.getAvailableStock() != null ? request.getAvailableStock() : 0);
        dish.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);

        return dishMapper.toResponseDTO(dishRepository.save(dish));
    }


    //update an existed dish
    @Transactional
    public DishResponseDTO updateDish(Long id, DishRequestDTO request) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Món ăn không tồn tại!"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại"));

        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setPrice(request.getPrice());
        dish.setImageUrl(request.getImageUrl());
        dish.setCategory(category);
        dish.setAvailableStock(request.getAvailableStock());
        dish.setIsActive(request.getIsActive());

        return dishMapper.toResponseDTO(dishRepository.save(dish));
    }

    //delete a dish
    @Transactional
    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Món ăn không tồn tại!"));

        //Soft delete
        dish.setIsActive(false);
        dishRepository.save(dish);
    }
}
