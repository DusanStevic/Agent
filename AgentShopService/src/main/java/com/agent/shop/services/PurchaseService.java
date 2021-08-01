package com.agent.shop.services;

import com.agent.shop.client.ProductClient;
import com.agent.shop.model.dto.ProductDTO;
import com.agent.shop.model.dto.PurchaseRequest;
import com.agent.shop.model.dto.ReportProductResponseDTO;
import com.agent.shop.model.dto.ReportResponseDTO;
import com.agent.shop.model.entity.Purchase;
import com.agent.shop.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public ReportResponseDTO report() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        List<ProductDTO> products = productClient.getProducts();
        ReportResponseDTO responseDTO = new ReportResponseDTO(new ArrayList<>());
        for (ProductDTO product : products) {
            List<Purchase> purchases = purchaseList.stream().filter(purchase -> purchase.getProductId().equals(product.getId())).collect(Collectors.toList());
            if (purchases.isEmpty()) {
                continue;
            }
            Integer unitSold = purchases.stream().map(Purchase::getAmount).reduce((sum, current) -> sum += current).get();
            Double profit = purchases.stream().map(Purchase::getTotal).reduce((sum, current) -> sum += current).get();
            ReportProductResponseDTO reportProductResponseDTO = new ReportProductResponseDTO(product.getName(), unitSold, profit);
            responseDTO.getProductResponse().add(reportProductResponseDTO);
        }
        return responseDTO;
    }
}
