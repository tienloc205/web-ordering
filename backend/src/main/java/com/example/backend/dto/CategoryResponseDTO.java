package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String name;
}
