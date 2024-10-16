package com.example.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is Required")

        String name,
        @NotNull(message = "Product description is Required")
        String description,
        @Positive(message = "available quantity must be positive")
        double availableQuantity,
        @Positive(message = "price must be positive")
        BigDecimal price,
        @NotNull(message = "category description is Required")
        Integer categoryId
        ) {
}
