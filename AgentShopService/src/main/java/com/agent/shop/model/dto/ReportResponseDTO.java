package com.agent.shop.model.dto;

import java.util.List;

public class ReportResponseDTO {
    List<ReportProductResponseDTO> productResponse;

    public ReportResponseDTO(List<ReportProductResponseDTO> productResponse) {
        this.productResponse = productResponse;
    }

    public List<ReportProductResponseDTO> getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(List<ReportProductResponseDTO> productResponse) {
        this.productResponse = productResponse;
    }
}
