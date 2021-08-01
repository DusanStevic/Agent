package com.agent.shop.services;

import com.agent.shop.client.ProductClient;
import com.agent.shop.model.dto.ProductDTO;
import com.agent.shop.model.dto.PurchaseRequest;
import com.agent.shop.model.entity.Product;
import com.agent.shop.model.entity.Purchase;
import com.agent.shop.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final ProductClient productClient;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(ProductClient productClient, PurchaseRepository purchaseRepository) {
        this.productClient = productClient;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase buy(PurchaseRequest request) throws Exception {
        ProductDTO product = productClient.getProduct(request.getProductId());
        if (product.getAvailable() < request.getQuantity()) {
            throw new Exception("Not enough available");
        }
        Double price = product.getPrice() * request.getQuantity();
        product.setAvailable(product.getAvailable() - request.getQuantity());
        Purchase purchase = new Purchase(request.getName(), request.getAddress(), request.getPhone(), product.getId(), request.getQuantity(), price);
        productClient.editProduct(product);
        return purchaseRepository.save(purchase);
    }
}
