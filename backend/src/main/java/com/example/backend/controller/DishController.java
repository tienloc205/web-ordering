package com.example.backend.controller;

import com.example.backend.dto.DishResponseDTO;
import com.example.backend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/")
    public List<DishResponseDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/menu")
    public Map<String, List<DishResponseDTO>> getMenu(){
        return dishService.getMenu();
    }
}
