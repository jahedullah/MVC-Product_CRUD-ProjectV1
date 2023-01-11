package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterResponseDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateResponseDto;
import com.Jahedullah.ProjectV1.string.PRODUCT_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

//    @GetMapping(value = PRODUCT_URL.PRODUCT_WITH_ID)
//    public ResponseEntity<Product> getProducts(@PathVariable int productId) {
//
//        Product product = productDao.getProduct(productId);
//        return ResponseEntity.ok(product);
//    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProductsList() {

        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);
    }

    @PostMapping()
    public ResponseEntity<ProductRegisterResponseDto>
    addProduct(@RequestBody
               ProductRegisterRequestDto productRegisterRequestDto) {
        ProductRegisterResponseDto productRegisterResponseDto =
                productDao.createProduct(productRegisterRequestDto);
        if (productRegisterResponseDto.getId() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productRegisterResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(value = PRODUCT_URL.PRODUCT_UPDATE_BY_ID)
    public ResponseEntity<ProductUpdateResponseDto>
    updateProduct(@PathVariable int productId,
                  @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        ProductUpdateResponseDto productUpdateResponseDto =
                productDao.updateProduct(productId, productUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productUpdateResponseDto);

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

