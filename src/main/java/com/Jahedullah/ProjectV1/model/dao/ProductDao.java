package com.Jahedullah.ProjectV1.model.dao;

import com.Jahedullah.ProjectV1.model.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {

    public void createProduct(Product product);

    public List<Product> getProducts();

    public void deleteProduct(int pid);

    public Product getProduct(int pid);

    public void updateProduct(Product product);

}
