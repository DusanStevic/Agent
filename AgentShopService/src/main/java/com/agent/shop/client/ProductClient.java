package com.agent.shop.client;

import com.agent.shop.model.dto.ProductDTO;
import com.agent.shop.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8080/agent-product-service/api/product", name = "ProductClient")
public interface ProductClient {
    @GetMapping("{id}")
    ProductDTO getProduct(@PathVariable("id") Long productId);
    @PutMapping
    ProductDTO editProduct(@RequestBody ProductDTO productDTO);
}
