package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationRequest;
import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationResponse;
import com.Jahedullah.ProjectV1.configuration.auth.RegisterRequest;
import com.Jahedullah.ProjectV1.model.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @ResponseBody
    @PostMapping("/")
    public String auth() {
        return "Hello from Authentication endpoint. :)";
    }

    @PostMapping("/Register/User")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }
    @PostMapping("/Register/Admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.authenticate(request, response));
    }
}
