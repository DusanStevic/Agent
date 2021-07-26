package com.agent.product.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Long available;

    @Column
    private String imagePath;

    public Product() {
    }

    public Product(String name, Double price, Long available, String imagePath) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.imagePath = imagePath;
    }

    public Product(Long id, String name, Double price, Long available, String imagePath) {
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
