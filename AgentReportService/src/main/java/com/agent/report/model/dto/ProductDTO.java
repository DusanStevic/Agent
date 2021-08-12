package com.agent.report.model.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Long available;
    private String imagePath;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, Long available, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
