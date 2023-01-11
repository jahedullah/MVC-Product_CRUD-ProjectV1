package com.Jahedullah.ProjectV1.model.dao;

import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterResponseDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateResponseDto;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {

    ProductRegisterResponseDto createProduct(ProductRegisterRequestDto productRegisterRequestDto);

    List<Product> getProducts();

    void deleteProduct(int pid);

    ProductUpdateResponseDto getProduct(int pid);

    ProductUpdateResponseDto updateProduct(int productId, ProductUpdateRequestDto productUpdateRequestDto);

    void updateProductCount(Product product);

    void updateProductUserList(Product product, User user);
    List findAllProductName();
    List findAllProductId();


}
