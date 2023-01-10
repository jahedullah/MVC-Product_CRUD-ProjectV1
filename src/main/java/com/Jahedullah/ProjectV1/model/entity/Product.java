package com.Jahedullah.ProjectV1.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    private long price;
    private int productCount;
    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<User> userList;

}
