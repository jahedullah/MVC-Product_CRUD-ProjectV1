package com.Jahedullah.ProjectV1.controllers;
import com.Jahedullah.ProjectV1.Dao.ProductDao;
import com.Jahedullah.ProjectV1.URL.PRODUCT_URL;
import com.Jahedullah.ProjectV1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {
    @Autowired
    private Product product;
    @Autowired
    private ProductDao productDao;

    @GetMapping(value = "/hiPostman")
    public String showPostman(){

        return "Hey, I am the postman. the new Buddy. Welcome to My Product Based Company.";

    }

    @GetMapping (value = PRODUCT_URL.PRODUCT_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProducts(@PathVariable int courseId){

        Product product = productDao.getProduct(courseId);
        return ResponseEntity.ok(product);

    }

    @GetMapping (value = PRODUCT_URL.PRODUCTS_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsList(){

        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);

    }
    @PostMapping(PRODUCT_URL.ADD_PRODUCT)
    public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product){

        productDao.createProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(PRODUCT_URL.UPDATE_PRODUCT_BY_ID)
    public String updateProduct(@PathVariable int courseId, @RequestBody Product productToUpdate){

        Product productCatch = productDao.getProduct(courseId);
        productCatch.setName(productToUpdate.getName());
        productCatch.setDescription(productToUpdate.getDescription());
        productCatch.setPrice(productToUpdate.getPrice());
        productDao.updateProduct(productCatch);
        return "Course has been updated successfully";

    }

    @DeleteMapping(PRODUCT_URL.DELETE_PRODUCT_BY_ID)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int courseId){
        try {
            productDao.deleteProduct(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

