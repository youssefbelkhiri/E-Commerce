package com.example.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is Mendatory")
        Integer productId,
        @NotNull(message = "Quantity is Mendatory")
        Integer quantity
) {
}
