package com.agent.report.model.dto;

public class ReportProductResponseDTO {
    private String productName;
    private Integer unitsSold;
    private Double profit;

    public ReportProductResponseDTO(String productName, Integer unitsSold, Double profit) {
        this.productName = productName;
        this.unitsSold = unitsSold;
        this.profit = profit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
