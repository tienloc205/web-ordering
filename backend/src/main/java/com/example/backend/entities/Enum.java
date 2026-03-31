package com.example.backend.entities;

public class Enum {
    public enum UserRole { ADMIN, STAFF }
    public enum TableStatus { EMPTY, OCCUPIED, RESERVED }
    public enum OrderStatus { PENDING, CONFIRMED, COOKING, SERVED, COMPLETED, CANCELLED }
    public enum PaymentStatus { UNPAID, PAID }
    public enum PaymentMethod { CASH, BANK_TRANSFER }
}
