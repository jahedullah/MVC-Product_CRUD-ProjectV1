package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.Dao.PostmanProductDao;
import com.Jahedullah.ProjectV1.Dao.ProductDao;
import com.Jahedullah.ProjectV1.models.PostmanProduct;
import com.Jahedullah.ProjectV1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/postman",method = RequestMethod.GET)
public class PostManController {
    @Autowired
    private Product product;
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
    @GetMapping (value = "/productswithid/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProducts(@PathVariable int courseId){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        Product product = productDao.getProduct(courseId);
        return ResponseEntity.ok(product);
    }

    @GetMapping (value = "/productslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsList(){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);
    }
    @PostMapping(value = "/addproduct")
    public String addProduct(@RequestBody Product product){
        productDao.createProduct(product);
        return "Course has been added successfully.";
    }

    @PutMapping("/updateproduct/{courseId}")
        public String updateProduct(@PathVariable int courseId, @RequestBody Product productToUpdate){
        Product productCatch = productDao.getProduct(courseId);
        productCatch.setName(productToUpdate.getName());
        productCatch.setDescription(productToUpdate.getDescription());
        productCatch.setPrice(productToUpdate.getPrice());
        productDao.updateProduct(productCatch);
        return "Course has been updated successfully";
    }

    @DeleteMapping("/deleteproduct/{courseId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int courseId){
        try {
            productDao.deleteProduct(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

