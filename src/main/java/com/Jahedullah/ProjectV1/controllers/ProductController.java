package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.string.PRODUCT_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Jahedullah.ProjectV1.model.entity.*;
import com.Jahedullah.ProjectV1.model.dao.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/Products")
public class ProductController {

    private final ProductDao productDao;

    @GetMapping(value = PRODUCT_URL.PRODUCT_WITH_ID)
    public ResponseEntity<Product> getProducts(@PathVariable int productId) {

        Product product = productDao.getProduct(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProductsList() {

        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);
    }

    @PostMapping()
    public String addProduct(@RequestBody Product product) {
        productDao.createProduct(product);
        return "Product has been added successfully.";
    }

    @PutMapping(value = PRODUCT_URL.PRODUCT_UPDATE_BY_ID)
    public String updateProduct(@PathVariable int productId,
                                @RequestBody Product productToUpdate) {
        Product productCatch = productDao.getProduct(productId);
        productCatch.setName(productToUpdate.getName());
        productCatch.setDescription(productToUpdate.getDescription());
        productCatch.setPrice(productToUpdate.getPrice());
        productDao.updateProduct(productCatch);
        return "Product has been updated successfully";
    }

    @DeleteMapping(value = PRODUCT_URL.PRODUCT_DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int productId) {
        try {
            productDao.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

