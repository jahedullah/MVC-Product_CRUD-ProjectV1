package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dao.UserDao;
import com.Jahedullah.ProjectV1.string.ADMIN_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private UserDao userDao;

    @DeleteMapping(ADMIN_URL.USER_DELETE_BY_EMAIL)
    public String deleteUserByEmail(@PathVariable String email){
        userDao.deleteByEmail(email);
        return "User has been deleted.";

    }
}
