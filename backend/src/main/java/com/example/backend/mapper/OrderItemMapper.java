package com.example.backend.mapper;

import com.example.backend.dto.OrderItemRequestDTO;
import com.example.backend.dto.OrderItemResponseDTO;
import com.example.backend.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
        if (orderItem == null) return null;

        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getDish() != null ? orderItem.getDish().getId() : null,
                orderItem.getDish() != null ? orderItem.getDish().getName() : null,
                orderItem.getQuantity(),
                orderItem.getPriceAtTime(),
                orderItem.getNote()
        );
    }

    public OrderItemRequestDTO toRequestDTO(OrderItem orderItem) {
        if (orderItem == null) return null;

        return new OrderItemRequestDTO(
                orderItem.getDish() != null ? orderItem.getDish().getId() : null,
                orderItem.getQuantity(),
                orderItem.getNote()
        );
    }
}
