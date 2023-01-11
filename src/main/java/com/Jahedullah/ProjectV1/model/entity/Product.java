package com.Jahedullah.ProjectV1.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String name;

    private String description;

    private double price;
    private int productCount;
    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<User> userList;

}
