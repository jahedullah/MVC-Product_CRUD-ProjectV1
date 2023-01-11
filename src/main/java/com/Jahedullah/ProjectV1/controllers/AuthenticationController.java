package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.model.dto.AuthenticationDto.AuthenticationRequestDto;
import com.Jahedullah.ProjectV1.model.dto.AuthenticationDto.AuthenticationResponseDto;
import com.Jahedullah.ProjectV1.model.dto.UserRegisterDto.UserRegisterRequestDto;
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
    public ResponseEntity<AuthenticationResponseDto> registerUser(
            @RequestBody UserRegisterRequestDto request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }

    @PostMapping(AUTH_URL.ADMIN_REGISTRATION)
    public ResponseEntity<AuthenticationResponseDto> registerAdmin(
            @RequestBody UserRegisterRequestDto request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.register(request, response));
    }

    @PostMapping(AUTH_URL.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto request,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.authenticate(request, response));
    }
}
