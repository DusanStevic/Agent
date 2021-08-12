package com.agent.shop.client;

import com.agent.shop.model.dto.ProductDTO;
import com.agent.shop.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(url = "${hostAddress.product}", name = "ProductClient")
public interface ProductClient {
    @GetMapping("{id}")
    ProductDTO getProduct(@PathVariable("id") Long productId);
    @GetMapping("")
    List<ProductDTO> getProducts();

    @PutMapping
    ProductDTO editProduct(@RequestBody ProductDTO productDTO);
}
