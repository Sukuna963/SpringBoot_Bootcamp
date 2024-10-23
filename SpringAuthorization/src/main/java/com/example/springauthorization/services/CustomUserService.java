package com.example.springauthorization.services;

import com.example.springauthorization.models.UserMeta;
import com.example.springauthorization.repositories.UserMetaRepo;
import com.example.springauthorization.models.UserPrincipal;
import com.example.springauthorization.repositories.UserPrincipalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Autowired
    private UserMetaRepo userMetaRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username or email: " + username
        ));
    }

    public UserMeta updateUserMeta(UserMeta userToUpdate) {
        UserMeta updatedUser = userMetaRepo.save(userToUpdate);

        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        userPrincipal.setUserMeta(userToUpdate);

        return updatedUser;
    }
}
