package com.example.basicauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('Role.ADMIN')")
public class AdminController {
    @GetMapping
    public String getAdmin(){
        return "Admin sahifasi";
    }
}