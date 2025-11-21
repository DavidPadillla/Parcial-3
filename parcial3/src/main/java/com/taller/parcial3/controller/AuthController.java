package com.taller.parcial3.controller;

import com.taller.parcial3.config.JwtUtil;
import com.taller.parcial3.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoElectronico(),
                        request.getContrasena()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtUtil.generateToken(request.getCorreoElectronico());
    }
}