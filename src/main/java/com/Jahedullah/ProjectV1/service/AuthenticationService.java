package com.Jahedullah.ProjectV1.service;

import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationRequest;
import com.Jahedullah.ProjectV1.configuration.auth.AuthenticationResponse;
import com.Jahedullah.ProjectV1.configuration.auth.RegisterRequest;
import com.Jahedullah.ProjectV1.dao.UserDao;
import com.Jahedullah.ProjectV1.entity.User;
import com.Jahedullah.ProjectV1.entity.role.AppUserRole;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request){
        User user = null;
        if(request.getUsertype().equals("user")) {
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .mobilenumber(request.getMobilenumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .usertype(request.getUsertype())
                    .appUserRole(AppUserRole.USER)
                    .build();
        } else if (request.getUsertype().equals("admin")) {
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .mobilenumber(request.getMobilenumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .usertype(request.getUsertype())
                    .appUserRole(AppUserRole.ADMIN)
                    .build();

        }

        userDao.save(user);
        var jwtToken = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userDao.findByEmail(request.getEmail());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }
}
