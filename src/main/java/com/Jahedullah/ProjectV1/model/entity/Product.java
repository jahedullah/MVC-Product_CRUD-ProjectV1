package com.Jahedullah.ProjectV1.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String name;

    private String description;

    private long price;
    private int productCount;
    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<User> userList;

}
