package com.example.springsecurity.services;

import com.example.springsecurity.models.UserPrincipal;
import com.example.springsecurity.repositories.UserPrincipalRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserPrincipalRepo principalRepo;

    public CustomUserDetailsService(UserPrincipalRepo principalRepo) {
        this.principalRepo = principalRepo;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return principalRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email: " + username));
    }
}
