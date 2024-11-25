package com.freshsip.freshsip.controller;



import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.service.UserManagementService;
import com.freshsip.freshsip.service.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserManagementService userManagementService;

    private ObjectMapper objectMapper;

    @MockBean
    private JWTUtils jwtUtils;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveUser() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("User saved successfully");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.saveUser(any(UserDTO.class))).thenReturn(mockResponse);

        UserDTO request = new UserDTO();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        mockMvc.perform(post("/FreshSip/adminUser/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User saved successfully"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testLoginUser() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("Login successful");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.loginUser(any(UserDTO.class))).thenReturn(mockResponse);

        UserDTO request = new UserDTO();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        mockMvc.perform(post("/FreshSip/adminUser/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testRefreshToken() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("Token refreshed successfully");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.refreshToken(any(UserDTO.class))).thenReturn(mockResponse);

        UserDTO request = new UserDTO();
        request.setToken("oldToken");

        mockMvc.perform(post("/FreshSip/admin/refreshToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Token refreshed successfully"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testGetAllUser() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("Successful");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.getAllUser()).thenReturn(mockResponse);

        mockMvc.perform(get("/FreshSip/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successful"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testGetUserByEmail() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("User found successfully");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.getUserByEmail(eq("test@example.com"))).thenReturn(mockResponse);

        mockMvc.perform(get("/FreshSip/adminUser/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User found successfully"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("Updated successfully");
        mockResponse.setStatusCode(200);

        User request = new User();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("test@example.com");

        Mockito.when(userManagementService.updateUser(eq("test@example.com"), any(User.class))).thenReturn(mockResponse);

        mockMvc.perform(put("/FreshSip/adminUser/test@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Updated successfully"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }

    @Test
    void testDeleteUser() throws Exception {
        UserDTO mockResponse = new UserDTO();
        mockResponse.setMessage("User deleted successfully");
        mockResponse.setStatusCode(200);

        Mockito.when(userManagementService.deleteUser(eq("test@example.com"))).thenReturn(mockResponse);

        mockMvc.perform(delete("/FreshSip/adminUser/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted successfully"))
                .andExpect(jsonPath("$.statusCode").value(200));
    }
}
