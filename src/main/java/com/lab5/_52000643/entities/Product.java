package com.lab5._52000643.entities;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private float price;

    public Product(int id, String productName, float price) {
        this.id = id;
        this.name = productName;
        this.price = price;
    }

    public int getProductId() {
        return id;
    }

    public void setProductId(int productId) {
        this.id = productId;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

