package com.api.adminhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.api.adminhr.model.User;
import com.api.adminhr.service.UserService;
import com.api.adminhr.config.JwtTokenUtil;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGetName() throws Exception {
        String token = "Bearer mockToken";

        String phoneNumber = "1234567890";
        String name = "John Doe";

        Mockito.when(jwtTokenUtil.validateToken(Mockito.anyString())).thenReturn(true);

        Mockito.when(jwtTokenUtil.getPhoneNumberFromToken(Mockito.anyString())).thenReturn(phoneNumber);
       
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        Mockito.when(userService.getNameByPhoneNumber(phoneNumber)).thenReturn(name);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/name")
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(name));
    }

    @Test
    public void testUpdateName() throws Exception {
        String token = "Bearer mockToken";

        String phoneNumber = "1234567890";
        String name = "John Doe";

        Mockito.when(jwtTokenUtil.validateToken(Mockito.anyString())).thenReturn(true);

        Mockito.when(jwtTokenUtil.getPhoneNumberFromToken(Mockito.anyString())).thenReturn(phoneNumber);

        Mockito.when(userService.doesUserExist(phoneNumber)).thenReturn(true);

        mockMvc.perform(put("/users/name")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new User(phoneNumber, name, null))))
                .andExpect(status().isOk())
                .andExpect(content().string("Name updated successfully"));

        Mockito.verify(userService).updateNameByPhoneNumber(phoneNumber, name);
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
