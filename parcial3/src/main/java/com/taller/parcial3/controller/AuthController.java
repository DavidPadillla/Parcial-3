package com.taller.parcial3.controller;

import com.taller.parcial3.config.JwtUtil;
import com.taller.parcial3.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        try {
            System.out.println(">>> INTENTO DE LOGIN: " + request.getCorreoElectronico());
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreoElectronico(),
                            request.getContrasena()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtil.generateToken(request.getCorreoElectronico());
            System.out.println(">>> LOGIN EXITOSO, TOKEN GENERADO");
            return Map.of("token", token);
        } catch (Exception e) {
            System.out.println(">>> ERROR EN LOGIN: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}