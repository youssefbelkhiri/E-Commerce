package com.example.ecommerce.order;

import com.example.ecommerce.customer.CustomerClient;
import com.example.ecommerce.exception.BusinessException;
import com.example.ecommerce.orderLine.OrderLineRequest;
import com.example.ecommerce.orderLine.OrderLineService;
import com.example.ecommerce.product.ProductClient;
import com.example.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final OrderLineService service;
    public Integer createOrder(@Valid OrderRequest req) {
        var customer = this.customerClient.getCustomer(req.customerId()).orElseThrow(() -> new BusinessException("cannot create an order:: No Customer Found"));
        this.productClient.purchaseProducts(req.products());
        var order = this.repo.save(mapper.toOrder(req));
        for (PurchaseRequest purchaseRequest:req.products()){
            service.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ) );
        }
        return null;
    }
}
