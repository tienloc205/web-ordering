package com.example.backend.mapper;

import com.example.backend.dto.TableResponseDTO;
import com.example.backend.entities.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {
    public TableResponseDTO toResponseDTO(RestaurantTable table) {
        if (table == null) return null;

        return new TableResponseDTO(
                table.getId(),
                table.getTableNumber(),
                table.getStatus().toString(),
                table.getQrCodeUrl()
        );
    }
}
