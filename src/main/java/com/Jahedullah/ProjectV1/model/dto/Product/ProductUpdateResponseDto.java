package com.Jahedullah.ProjectV1.model.dto.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Setter @Getter
public class ProductUpdateResponseDto {
    private String name;
    private String description;
    private double price;
    private int productCount;

}
