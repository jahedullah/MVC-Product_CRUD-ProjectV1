package com.Jahedullah.ProjectV1.model.dao;

import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {

    void createProduct(Product product);

    List<Product> getProducts();

    void deleteProduct(int pid);

    Product getProduct(int pid);

    void updateProduct(Product product);
    void updateProductCount(Product product);
    void updateProductUserList(Product product, User user);

}
