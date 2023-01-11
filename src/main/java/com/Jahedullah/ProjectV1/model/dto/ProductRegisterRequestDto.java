package com.Jahedullah.ProjectV1.model.dto;

import lombok.*;

@Getter @Setter
public class ProductRegisterRequestDto {
    private String name;
    private String description;
    private double price;
    private int productCount;

}
