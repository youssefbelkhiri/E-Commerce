package com.example.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer FirstName is required")
        String firstName,
        @NotNull(message = "Customer LastName is required")
        String lastName,
        @NotNull(message = "Customer Email is required")
        @NotNull(message = "Customer Email is not a valid Email Address")
        String email,
        Address address) {
}
