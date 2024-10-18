package com.example.ecommerce.order;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(@Valid OrderRequest req) {
        return Order.builder()
                .id(req.id())
                .customerId(req.customerId())
                .reference(req.reference())
                .totalAmount(req.amount())
                .paymentMethod(req.paymentMethod())
                .build();
    }
}
