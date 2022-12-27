package com.Jahedullah.ProjectV1.controllers;
import com.Jahedullah.ProjectV1.URL.ProductURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Jahedullah.ProjectV1.entity.*;
import com.Jahedullah.ProjectV1.dao.*;
import java.util.List;

@RestController
@RequestMapping(value = "/",method = RequestMethod.GET)
public class ProductController {
    @Autowired
    private ProductDao productDao;

//    @ResponseBody
    @GetMapping(value = "/hi")
    public String showPostman(){
        return "Hey, I am the postman. the new Buddy.";
    }

    @GetMapping (value = ProductURL.PRODUCT_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProducts(@PathVariable int courseId){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        Product product = productDao.getProduct(courseId);
        return ResponseEntity.ok(product);
    }

    @GetMapping (value = ProductURL.PRODUCT_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsList(){

//        return new ResponseEntity<>(postmanProductDao.getProducts(), HttpStatus.ACCEPTED);
        List<Product> productsList = productDao.getProducts();
        return ResponseEntity.ok(productsList);
    }
    @PostMapping(value = ProductURL.PRODUCT_ADD)
    public String addProduct(@RequestBody Product product){
        productDao.createProduct(product);
        return "Course has been added successfully.";
    }

    @PutMapping(value = ProductURL.PRODUCT_UPDATE_BY_ID)
        public String updateProduct(@PathVariable int courseId, @RequestBody Product productToUpdate){
        Product productCatch = productDao.getProduct(courseId);
        productCatch.setName(productToUpdate.getName());
        productCatch.setDescription(productToUpdate.getDescription());
        productCatch.setPrice(productToUpdate.getPrice());
        productDao.updateProduct(productCatch);
        return "Course has been updated successfully";
    }

    @DeleteMapping(value = ProductURL.PRODUCT_DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int courseId){
        try {
            productDao.deleteProduct(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

