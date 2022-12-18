package com.Jahedullah.ProjectV1.models;

public class PostmanProduct {
    private int productId;
    private String productName;
    private String productVendor;

    public PostmanProduct() {
    }

    public PostmanProduct(int productId, String productName, String productVendor) {
        this.productId = productId;
        this.productName = productName;
        this.productVendor = productVendor;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }
}
