package com.Jahedullah.ProjectV1.model.dto.Product;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ProductRegisterRequestDto {

    private String name;

    private String description;

    private double price;
    private int productCount;

}
