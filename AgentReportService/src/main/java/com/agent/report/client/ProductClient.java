package com.agent.report.client;

import com.agent.report.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// app.product.url from application.properties
@FeignClient(name = "product", url = "${app.product.url}")
public interface ProductClient {
    @GetMapping("{id}")
    ProductDTO getProduct(@PathVariable("id") Long productId);
    @GetMapping("")
    List<ProductDTO> getProducts();

    @PutMapping
    ProductDTO editProduct(@RequestBody ProductDTO productDTO);
}
