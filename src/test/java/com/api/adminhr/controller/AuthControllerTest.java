package com.api.adminhr.controller;

import com.api.adminhr.model.User;
import com.api.adminhr.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        // Mock input
        User user = new User();
        user.setPhoneNumber("1234567890");
        user.setName("John Doe");
        user.setPassword("Password123");

        // Mock service response
        when(userService.registerUser(user.getPhoneNumber(), user.getName(), user.getPassword())).thenReturn(user);

        // Make the API call
        ResponseEntity<String> response = authController.registerUser(user);

        // Verify the service method was called
        verify(userService, times(1)).registerUser(user.getPhoneNumber(), user.getName(), user.getPassword());

        // Verify the API response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }
}
