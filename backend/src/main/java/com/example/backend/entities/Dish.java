package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    @Column(name = "available_stock")
    private Integer availableStock = 0;

    @Column(name = "is_active")
    private Boolean isActive = true;
    private LocalDateTime createdAt = LocalDateTime.now();
}
