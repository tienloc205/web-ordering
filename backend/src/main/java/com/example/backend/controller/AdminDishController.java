package com.example.backend.controller;


import com.example.backend.dto.DishRequestDTO;
import com.example.backend.dto.DishResponseDTO;
import com.example.backend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/dish")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminDishController {
    private final DishService dishService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createDish(@RequestBody DishRequestDTO request) {
        try {
            DishResponseDTO newDish = dishService.createDish(request);

            // Trả về JSON gọn gàng và có thông báo rõ ràng
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "Thêm món ăn '" + newDish.getName() + "' thành công!",
                    "dishId", newDish.getId(),
                    "status", "success"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Không thể thêm món ăn",
                    "error", e.getMessage()
            ));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody DishRequestDTO request) {
        try {
            DishResponseDTO updatedDish = dishService.updateDish(id, request);

            return ResponseEntity.ok(Map.of(
                    "message", "Cập nhật món ăn '" + updatedDish.getName() + "' thành công",
                    "dishId", updatedDish.getId(),
                    "status", "success"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Cập nhật thất bại",
                    "error", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        try {
            dishService.deleteDish(id);

            return ResponseEntity.ok(Map.of(
                    "message", "Đã xóa/ẩn món ăn thành công",
                    "dishId", id,
                    "status", "success"
            ));
        } catch (Exception e) {
            // Lỗi thường gặp: Không tìm thấy ID hoặc vi phạm ràng buộc dữ liệu
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Xóa thất bại",
                    "error", e.getMessage()
            ));
        }
    }
}
