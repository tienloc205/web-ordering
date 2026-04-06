package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {
    private Long tableId;
    private String paymentMethod; // "CASH" hoặc "BANK_TRANSFER"
    private List<OrderItemRequestDTO> items;
}
