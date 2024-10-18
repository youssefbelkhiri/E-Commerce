package com.example.ecommerce.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repo;
    private final OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return repo.save(order).getId();
    }
}
