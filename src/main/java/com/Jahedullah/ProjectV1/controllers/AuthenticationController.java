package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationRequest;
import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationResponse;
import com.Jahedullah.ProjectV1.configuration.auth.RegisterRequest;
import com.Jahedullah.ProjectV1.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    @ResponseBody
    @PostMapping("/")
    public String auth(){
        return "Hello from Authentication endpoint. :)";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
            )
    {
        return ResponseEntity.ok(authService.register(request,response));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
