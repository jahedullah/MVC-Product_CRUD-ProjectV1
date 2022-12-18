package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.Dao.PostmanProductDao;
import com.Jahedullah.ProjectV1.Dao.ProductDao;
import com.Jahedullah.ProjectV1.models.PostmanProduct;
import com.Jahedullah.ProjectV1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/postman",method = RequestMethod.GET)
public class PostManController {
    @Autowired
    private ProductDao productDao;

//    @ResponseBody
    @GetMapping(value = "/hi")
    public String showPostman(){
        return "Hey, I am the postman. the new Buddy.";
    }

//    @ResponseBody

//    @GetMapping (value = "/Products")
//    @ResponseBody
//    public PostmanProduct getProducts(){
//        PostmanProduct postmanProduct = new PostmanProduct(103, "s2", "Samsung");
////        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
//        return postmanProduct;
//    }
    @GetMapping (value = "/productswithid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProducts(){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        Product product = productDao.getProduct(13);
        return ResponseEntity.ok(product);
    }

    @GetMapping (value = "/productslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsList(){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);
    }
}
