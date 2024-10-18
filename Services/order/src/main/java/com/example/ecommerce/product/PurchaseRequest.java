package com.example.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is menatory")
        Integer productId,
        @Positive(message = "quantity should be positive")
        double quantity
) {
}
