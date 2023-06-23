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

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getName_Success() {
        // Mock input
        String phoneNumber = "1234567890";
        String token = "valid_token";

        // Mock service response
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setName("John Doe");
        when(userService.getUserByPhoneNumber(phoneNumber)).thenReturn(user);

        // Make the API call
        ResponseEntity<String> response = userController.getName(token);

        // Verify the service method was called
        verify(userService, times(1)).getUserByPhoneNumber(phoneNumber);

        // Verify the API response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user.getName(), response.getBody());
    }

    @Test
    void updateName_Success() {
        // Mock input
        String phoneNumber = "1234567890";
        String newName = "Jane Smith";
        String token = "valid_token";

        // Mock service response
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setName("John Doe");
        when(userService.updateUserName(phoneNumber, newName)).thenReturn(user);

        // Make the API call
        ResponseEntity<String> response = userController.updateName(token, user);

        // Verify the service method was called
        verify(userService, times(1)).updateUserName(phoneNumber, newName);

        // Verify the API response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Name updated successfully", response.getBody());
    }
}
