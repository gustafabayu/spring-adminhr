package com.api.adminhr.controller;

import com.api.adminhr.model.User;
import com.api.adminhr.service.UserService;
import com.api.adminhr.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(@RequestHeader("Authorization") String token) {
       String phoneNumber = jwtTokenUtil.extractPhoneNumberFromToken(token);

        if (userService.doesUserExist(phoneNumber)) {
            String name = userService.getNameByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(name);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number not found");
        }

    }

    @PutMapping("/name")
    public ResponseEntity<String> updateName(@RequestHeader("Authorization") String token, @RequestBody User user) {
        String phoneNumber = jwtTokenUtil.extractPhoneNumberFromToken(token);

        if (userService.doesUserExist(phoneNumber)) {
            userService.updateNameByPhoneNumber(phoneNumber, user.getName());
            return ResponseEntity.ok("Name updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number not found");
        }
    }
}
