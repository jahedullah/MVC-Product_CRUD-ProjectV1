package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dto.Product.*;
import com.Jahedullah.ProjectV1.string.PRODUCT_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Jahedullah.ProjectV1.model.entity.*;
import com.Jahedullah.ProjectV1.model.dao.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/Products")
public class ProductController {

    private final ProductDao productDao;

    @GetMapping(PRODUCT_URL.PRODUCT_WITH_ID)
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable int productId) throws NullPointerException {

        Optional<Product> product = Optional.ofNullable(productDao.getProduct(productId));
        if (product.isPresent()) {
            ProductDto productUpdateResponseDto = ProductDto.builder()
                    .id(product.get().getId())
                    .name(product.get().getName())
                    .description(product.get().getDescription())
                    .price(product.get().getPrice())
                    .productCount(product.get().getProductCount())
                    .build();
            return ResponseEntity.ok(productUpdateResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProductsList() {
        List<ProductDto> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);

    }

    @PostMapping()
    public ResponseEntity<ProductRegisterResponseDto>
    addProduct(@RequestBody
               ProductRegisterRequestDto productRegisterRequestDto) {
        ProductRegisterResponseDto productRegisterResponseDto =
                productDao.createProduct(productRegisterRequestDto);
        if (productRegisterRequestDto.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } else if (productRegisterResponseDto.getId() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productRegisterResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(value = PRODUCT_URL.PRODUCT_UPDATE_BY_ID)
    public ResponseEntity<ProductDto>
    updateProduct(@PathVariable int productId,
                  @RequestBody ProductUpdateRequestDto productUpdateRequestDto) throws NullPointerException {
        try {
            ProductDto productDto =
                    productDao.updateProduct(productId, productUpdateRequestDto);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDto);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping(value = PRODUCT_URL.PRODUCT_DELETE_BY_ID)
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable int productId) throws NullPointerException {
        try {
            ProductDto productDto =
                    productDao.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

