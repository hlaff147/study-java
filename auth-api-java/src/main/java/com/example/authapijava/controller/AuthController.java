package com.example.authapijava.controller;

import com.example.authapijava.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }

    @GetMapping("/secured")
    public String securedEndpoint() {
        return "This is a secured endpoint!";
    }
}