package com.example.ecommerce.product;

import java.math.BigDecimal;

public record ProductPuchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
