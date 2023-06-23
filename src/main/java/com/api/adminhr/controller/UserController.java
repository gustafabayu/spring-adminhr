package com.api.adminhr.controller;

import com.api.adminhr.model.User;
import com.api.adminhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(@RequestHeader("Authorization") String token) {
        // Extract phone number from JWT token
        // Check phone number exists
        // Get the name
        return ResponseEntity.ok("User's name");
    }

    @PutMapping("/name")
    public ResponseEntity<String> updateName(@RequestHeader("Authorization") String token, @RequestBody User user) {
        // Extract phone number from JWT token
        // Check phone number exists
        // Update the name
        return ResponseEntity.ok("Name updated successfully");
    }
}
