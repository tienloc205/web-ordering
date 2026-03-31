package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restaurant_tables")
@Data
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tableNumber;

    @Enumerated(EnumType.STRING)
    private Enum.TableStatus status = Enum.TableStatus.EMPTY;

    private String qrCodeUrl;
}