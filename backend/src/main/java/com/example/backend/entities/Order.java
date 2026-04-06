package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private Constants.OrderStatus status = Constants.OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Constants.PaymentStatus paymentStatus = Constants.PaymentStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    private Constants.PaymentMethod paymentMethod;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}


