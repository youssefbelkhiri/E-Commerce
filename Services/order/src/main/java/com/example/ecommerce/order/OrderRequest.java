package com.example.ecommerce.order;

import com.example.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer method should be precised")
        @NotEmpty(message = "customer method should be precised")
        @NotBlank(message = "customer method should be precised")
        String customerId,
        @NotEmpty(message = "you should purchase at least one product")
        List<PurchaseRequest> products
) {
}
