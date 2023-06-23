package com.api.adminhr.controller;

import com.api.adminhr.model.User;
import com.api.adminhr.service.UserService;
import com.api.adminhr.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user.getPhoneNumber(), user.getName(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String phoneNumber = user.getPhoneNumber();
        String password = user.getPassword();

        User retrievedUser = userService.getUserByPhoneNumber(phoneNumber);

        if (retrievedUser != null && passwordEncoder.matches(password, retrievedUser.getPassword())) {
            String token = jwtTokenUtil.generateToken(phoneNumber);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // private String generateToken(String phoneNumber) {
    // // Generate the JWT token using your preferred library (e.g., jjwt)
    // // Set the required claims (e.g., subject, expiration)
    // // Sign the token with your asymmetric keys or secret key

    // return "JWT token"; // Replace with the actual generated token
    // }

}
