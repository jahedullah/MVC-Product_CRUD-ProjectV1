package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dao.UserDao;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.string.USER_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserDao userDao;

    @PutMapping(USER_URL.USER_BUY_PRODUCT_BY_ID)
    public String buyProduct(@PathVariable int id, HttpServletRequest request) {
        return userDao.buyProductByID(id, request);

    }

    @GetMapping(USER_URL.USER_PRODUCTS_LIST)
    public ResponseEntity<List<Product>> productsList(HttpServletRequest request) {
        List<Product> productList = userDao.productsList(request);
        return ResponseEntity.ok(productList);
    }

    @PutMapping(USER_URL.USER_PRODUCTS_DELETE_BY_ID)
    public ResponseEntity<HttpStatus> productsDeleteById(@PathVariable int pid,
                                                         HttpServletRequest request) {
        try {
            userDao.productsDeleteById(pid, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
