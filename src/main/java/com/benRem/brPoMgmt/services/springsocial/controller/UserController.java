package com.benRem.brPoMgmt.services.springsocial.controller;

import com.benRem.brPoMgmt.services.springsocial.exception.ResourceNotFoundException;
import com.benRem.brPoMgmt.services.springsocial.model.User;
import com.benRem.brPoMgmt.services.springsocial.repository.UserRepository;
import com.benRem.brPoMgmt.services.springsocial.security.CurrentUser;
import com.benRem.brPoMgmt.services.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
