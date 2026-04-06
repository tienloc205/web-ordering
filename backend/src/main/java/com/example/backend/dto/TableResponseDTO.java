package com.example.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableResponseDTO {
    private Long id;
    private String tableNumber;
    private String status;
    private String qrCodeUrl;
}
