package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest req) {
        return Product.builder()
                .id(req.id())
                .name(req.name())
                .description(req.description())
                .price(req.price())
                .availableQuantity(req.availableQuantity())
                .category(Category.builder().id(req.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPuchaseResponse toProductPurchaseResponse(Product product, @NotNull(message = "Quantity is Mendatory") Integer quantity) {
        return new ProductPuchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
