package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemRequestDTO {
    private Long dishId;
    private Integer quantity;
    private String note; // Ghi chú riêng cho từng món
}
