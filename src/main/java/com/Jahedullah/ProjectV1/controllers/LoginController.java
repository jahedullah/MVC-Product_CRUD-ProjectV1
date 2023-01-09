package com.Jahedullah.ProjectV1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {


    @ResponseBody
    @GetMapping
    public String homepage() {
        return "Welcome to ProjectV1 Home";
    }

    @GetMapping("login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("process-login")
    public String loginSuccess() {
        return "process-login";
    }

}