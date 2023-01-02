package com.Jahedullah.ProjectV1.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {


    @ResponseBody
    @GetMapping
    public String homepage(){
        return "Welcome to ProjectV1 Home";
    }

    @GetMapping("login")
    public String loginPage() {

        return "login";
    }
    @GetMapping("process-login")
    public String loginSuccess(){
        return "process-login";
    }
//    @ResponseBody
//    @GetMapping("/error")
//    public String error(){
//        return "Something is error here.";
//    }
//
//    @GetMapping("/signUp")
//    public String signUpPage(@ModelAttribute("signupdto") SignUpDTO signUpDTO){
//
//        return "signup";
//    }
//
//    @PostMapping("/process-signup")
//    public String processLoginPage(SignUpDTO signUpDTO){
//
//        System.out.println(signUpDTO);
//        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
//        signUpDTO.setPassword(encodedPassword);
//        System.out.println(signUpDTO);
//        signUpDao.saveUser(signUpDTO);
//        return "redirect:/myCustomLogin";
//    }
//    @ResponseBody
//    @PostMapping(value = "/restSignUp")
//    public String saveUser(@RequestBody SignUpDTO signUpDTO){
//        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
//        signUpDTO.setPassword(encodedPassword);
//        signUpDao.saveUser(signUpDTO);
//        return "User has been added successfully.";
//    }



}