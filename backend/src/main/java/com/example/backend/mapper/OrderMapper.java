package com.example.backend.mapper;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTO;
import com.example.backend.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponseDTO toResponseDTO(Order order) {
        if (order == null) return null;

        return new OrderResponseDTO(
                order.getId(),
                order.getTable() != null ? order.getTable().getId() : null,
                order.getTable() != null ? order.getTable().getTableNumber() : null,
                order.getTotalPrice(),
                order.getStatus() != null ? order.getStatus().name() : null,
                order.getPaymentStatus() != null ? order.getPaymentStatus().name() : null,
                order.getPaymentMethod() != null ? order.getPaymentMethod().name() : null,
                order.getCreatedAt(),
                order.getOrderItems() != null ? order.getOrderItems().stream()
                        .map(orderItemMapper::toResponseDTO)
                        .collect(Collectors.toList()) : null
        );
    }

    public OrderRequestDTO toRequestDTO(Order order) {
        if (order == null) return null;

        return new OrderRequestDTO(
                order.getTable() != null ? order.getTable().getId() : null,
                order.getPaymentMethod() != null ? order.getPaymentMethod().name() : null,
                order.getOrderItems() != null ? order.getOrderItems().stream()
                        .map(orderItemMapper::toRequestDTO)
                        .collect(Collectors.toList()) : null
        );
    }
}
