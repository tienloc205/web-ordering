package com.example.backend.controller;


import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.entities.Order;
import com.example.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Cho phép React gọi API
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO request) {
        try {
            Order newOrder = orderService.placeOrder(request);

            return ResponseEntity.ok(Map.of(
                    "message", "Đặt món thành công",
                    "orderId", newOrder.getId(),
                    "totalPrice", newOrder.getTotalPrice()
            ));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
        }
    }
}
