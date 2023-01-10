package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dao.UserDao;
import com.Jahedullah.ProjectV1.string.USER_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
}
