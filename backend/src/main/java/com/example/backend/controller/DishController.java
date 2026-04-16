package com.example.backend.controller;

import com.example.backend.dto.DishResponseDTO;
import com.example.backend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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

    @GetMapping("/search")
    public ResponseEntity<Map<String, List<DishResponseDTO>>> search(@RequestParam String keyword) {
        // Nếu từ khóa rỗng, trả về map trống hoặc xử lý tùy ý
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.ok(dishService.getMenu());
        }

        Map<String, List<DishResponseDTO>> results = dishService.searchDishesGroupedByCategory(keyword);
        return ResponseEntity.ok(results);
    }
}
