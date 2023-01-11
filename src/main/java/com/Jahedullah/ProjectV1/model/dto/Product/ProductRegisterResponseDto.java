package com.Jahedullah.ProjectV1.model.dto.Product;

import lombok.*;

@Builder @Getter
public class ProductRegisterResponseDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private int productCount;
}
