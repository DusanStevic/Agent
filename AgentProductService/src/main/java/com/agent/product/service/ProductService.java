package com.agent.product.service;

import com.agent.product.converter.ProductConverter;
import com.agent.product.model.dto.ProductDTO;
import com.agent.product.model.entity.Product;
import com.agent.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = ProductConverter.convertToProduct(productDTO);
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.getById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
