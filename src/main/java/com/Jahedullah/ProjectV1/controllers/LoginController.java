package com.Jahedullah.ProjectV1.controllers;
import com.Jahedullah.ProjectV1.dao.SignUpDao;
import com.Jahedullah.ProjectV1.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignUpDao signUpDao;

    @ResponseBody
    @GetMapping
    public String homepage(){
        return "Welcome to ProjectV1 Home";
    }

    @GetMapping("/myCustomLogin")
    public String loginPage() {

        return "login";

    }

    @GetMapping("/signUp")
    public String signUpPage(@ModelAttribute("signupdto") SignUpDTO signUpDTO){

        return "signup";
    }

    @PostMapping("/process-signup")
    public String processLoginPage(SignUpDTO signUpDTO){

        System.out.println(signUpDTO);
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        signUpDTO.setPassword(encodedPassword);
        System.out.println(signUpDTO);
        signUpDao.saveUser(signUpDTO);
        return "redirect:/myCustomLogin";
    }



}