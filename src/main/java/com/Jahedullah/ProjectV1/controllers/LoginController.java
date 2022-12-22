package com.Jahedullah.ProjectV1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @GetMapping("/myCustomLogin")
    public String customLogin(){
        return "login";
    }
}
