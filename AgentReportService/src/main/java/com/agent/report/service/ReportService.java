package com.agent.report.service;

import com.agent.report.client.ProductClient;
import com.agent.report.client.PurchaseClient;
import com.agent.report.model.dto.ProductDTO;
import com.agent.report.model.dto.ReportProductResponseDTO;
import com.agent.report.model.dto.ReportResponseDTO;
import com.agent.report.model.entity.Purchase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final PurchaseClient purchaseClient;
    private final ProductClient productClient;

    public ReportService(PurchaseClient purchaseClient, ProductClient productClient) {
        this.purchaseClient = purchaseClient;
        this.productClient = productClient;
    }


    public ReportResponseDTO report() {
        List<Purchase> purchaseList = purchaseClient.getPurchases();
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
