package com.Jahedullah.ProjectV1.model.dto.Product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductUpdateRequestDto {
    private String name;
    private String description;
    private double price;
    private int productCount;

}
