package com.example.ecommerce.orderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(Integer id, Integer orderId, @NotNull(message = "Product is menatory") Integer productId,
                               @Positive(message = "quantity should be positive") double quantity) {
}
