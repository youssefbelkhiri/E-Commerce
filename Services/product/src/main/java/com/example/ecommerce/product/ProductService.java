package com.example.ecommerce.product;

import com.example.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;
    public Integer createProduct(@Valid ProductRequest req) {
        var product = mapper.toProduct(req);
        return repo.save(product).getId();
    }

    public List<ProductPuchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> req) {
        var productIds = req.stream().map(ProductPurchaseRequest::productId).toList();
        var storesProducts = repo.findAllByIdInOrderById(productIds);
        if(productIds.size() != storesProducts.size()) {
            throw new ProductPurchaseException("one or more product does not exist");
        }
        var storedRequest = req.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPuchaseResponse>();
        for(int i=0 ; i<storesProducts.size() ; i++) {
            var product = storesProducts.get(i);
            var productRequest = req.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("not enough quantity");
            }
            var availableQuantity = productRequest.quantity() - product.getAvailableQuantity();
            product.setAvailableQuantity(availableQuantity);
            repo.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
    public ProductResponse findById(@Valid Integer productId) {
        return repo.findById(productId).map(mapper::toProductResponse).orElseThrow(() -> new EntityNotFoundException("Product Not Found"));
    }

    public List<ProductResponse> findAll() {
        return repo.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
