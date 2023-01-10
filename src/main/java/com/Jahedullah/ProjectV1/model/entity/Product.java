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
    @Column(name = "price")
    private long price;
    private int productCount;
    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<User> userList;

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", productCount=" + productCount +
//                ", userList=" + getUserEmailList(userList) +
//                '}';
//    }
//    private String getUserEmailList(Collection<User> userList){
//        String userEmailList = "";
//        for(User user : userList){
//            userEmailList += user.getEmail() + " ";
//        }
//        return userEmailList;
//    }
}
