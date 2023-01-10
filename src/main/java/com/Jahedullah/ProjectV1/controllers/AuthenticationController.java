package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.authDto.AuthenticationRequest;
import com.Jahedullah.ProjectV1.model.authDto.AuthenticationResponse;
import com.Jahedullah.ProjectV1.model.authDto.RegisterRequest;
import com.Jahedullah.ProjectV1.model.service.AuthenticationService;
import com.Jahedullah.ProjectV1.string.AUTH_URL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;


    @PostMapping(AUTH_URL.USER_REGISTRATION)
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }
    @PostMapping(AUTH_URL.ADMIN_REGISTRATION)
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }

    @PostMapping(AUTH_URL.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.authenticate(request, response));
    }
}
