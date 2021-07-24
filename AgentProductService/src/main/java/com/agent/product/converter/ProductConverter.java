package com.agent.product.converter;

import com.agent.product.model.dto.ProductDTO;
import com.agent.product.model.entity.Product;

public class ProductConverter {

    public static Product convertToProduct(ProductDTO dto) {
        return new Product(dto.getId(), dto.getName(), dto.getPrice(), dto.getAvailable(), dto.getImagePath());
    }

    public static ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getAvailable(), product.getImagePath());
    }
}
