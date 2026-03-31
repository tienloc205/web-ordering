package com.example.backend.dto;

import com.example.backend.entities.Enum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderRequestDTO {
    private Long tableId;
    private String paymentMethod = String.valueOf(Enum.PaymentMethod.CASH); // "CASH" hoặc "BANK_TRANSFER"
    private List<OrderItemRequestDTO> items;
}
