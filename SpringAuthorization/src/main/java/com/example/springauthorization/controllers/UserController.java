package com.example.springauthorization.controllers;

import com.example.springauthorization.models.UserMeta;
import com.example.springauthorization.models.UserPrincipal;
import com.example.springauthorization.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    CustomUserService customUserService;

    @PostMapping("/update-user")
    public UserMeta updateUser(@RequestBody UserMeta userToUpdate) {
        return customUserService.updateUserMeta(userToUpdate);
    }

    @GetMapping("/current-user")
    public UserMeta getUser(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return principal.getUserMeta();
    }
}
