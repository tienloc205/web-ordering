package com.example.backend.controller;

import com.example.backend.dto.DishResponseDTO;
import com.example.backend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DishController {
    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishResponseDTO>> getAllDishes() {
        List<DishResponseDTO> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/menu")
    public ResponseEntity<Map<String, List<DishResponseDTO>>> getMenu(){
        Map<String, List<DishResponseDTO>> menu = dishService.getMenu();
        return ResponseEntity.ok(menu);
    }
}
