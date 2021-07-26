package com.agent.product.controller;

import com.agent.product.converter.ProductConverter;
import com.agent.product.model.dto.ProductDTO;
import com.agent.product.model.entity.Product;
import com.agent.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(productDTO);
        ProductDTO responseDTO = ProductConverter.convertToDTO(product);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
