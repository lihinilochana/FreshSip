package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserManagementServiceImplTest {
    @InjectMocks
    private UserManagementServiceImpl userManagementService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setNic("200150103490");
        userDTO.setDob(LocalDate.of(2001, 1, 1));
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");
        userDTO.setMobileNumber("0759075987");
    }

    @Test
    void testAddingUserSuccessfully() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

        User newUser = new User();
        when(modelMapper.map(userDTO, User.class)).thenReturn(newUser);

        UserDTO response = userManagementService.saveUser(userDTO);

        assertEquals(200, response.getStatusCode());
        assertEquals("User save successfully.", response.getMessage());
        verify(userRepository).save(newUser);
    }

    @Test
    void testAddUserWithExistingEmail() {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");

        User existingUser = new User();
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(existingUser));

        UserDTO response = userManagementService.saveUser(userDTO);

        assertNotNull(response, "Response should not be null");
        assertEquals(0, response.getStatusCode(), "Default statusCode should be 0 in the method");
        assertEquals("You have already used your email to sign in. Please use different email.",
                response.getMessage(),
                "Error message should indicate email is already in use");

        verify(userRepository).findByEmail(userDTO.getEmail());
    }


    @Test
    void testLoginUserWithValidCredentials() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);

        User mockUser = new User();
        mockUser.setEmail(userDTO.getEmail());
        mockUser.setPassword(userDTO.getPassword());
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(mockUser));

        String mockJwtToken = "mockJwtToken123";
        String mockRefreshToken = "mockJwtToken123";
        when(jwtUtils.generateToken(any(User.class))).thenReturn(mockJwtToken);
        when(jwtUtils.generateRefreshToken(any(), any(User.class))).thenReturn(mockRefreshToken);

        UserDTO response = userManagementService.loginUser(userDTO);

        assertEquals(200, response.getStatusCode());
        assertEquals(mockJwtToken, response.getToken());
        assertEquals(mockRefreshToken, response.getRefreshToken());
        assertEquals("Login Success", response.getMessage());
        assertEquals(userDTO.getEmail(), response.getEmail());
        assertEquals(userDTO.getUserType(), response.getUserType());
    }

    @Test
    void testLoginUserWithInvalidCredentials() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        UserDTO response = userManagementService.loginUser(userDTO);

        assertEquals(500, response.getStatusCode());
        assertEquals("Invalid credentials", response.getMessage());
    }


//    @Test
//    void testGetAllUser_Success() {
//
//        List<User> mockUsers = Arrays.asList(
//                new User(1, "John", "Doe", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0812316433", "USER"),
//                new User(2, "Jane", "Doe", "198712587955", (LocalDate.of(1987,8,22)), "jane.doe@example.com", "654321", "011654321", "ADMIN")
//        );
//
//        when(userRepository.findAll()).thenReturn(mockUsers);
//
//        UserDTO userDTO = userManagementService.getAllUser();
//
//        assertNotNull(userDTO);
//        assertEquals(200, userDTO.getStatusCode());
//        assertEquals("Successful", userDTO.getMessage());
//        assertEquals(2, userDTO.getUserList().size());
//    }

    @Test
    void testGetAllUser_Exception() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));

        UserDTO userDTO = userManagementService.getAllUser();

        assertNotNull(userDTO);
        assertEquals(500, userDTO.getStatusCode());
        assertTrue(userDTO.getMessage().contains("Error occurred: Database connection error"));
    }

//    @Test
//    void testGetUserByEmail_Success() {
//
//        String email = "john.doe@example.com";
//        User mockUser = new User(1, "John", "Doe", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0812316433", "USER");
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
//
//        UserDTO userDTO = userManagementService.getUserByEmail(email);
//
//        assertNotNull(userDTO);
//        assertEquals(200, userDTO.getStatusCode());
//        assertEquals("User with ID " + email + " found successfully.", userDTO.getMessage());
//        assertEquals(mockUser, userDTO.getUser());
//    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        String email = "unknown@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserDTO userDTO = userManagementService.getUserByEmail(email);

        assertNotNull(userDTO);
        assertEquals(500, userDTO.getStatusCode());
        assertTrue(userDTO.getMessage().contains("Error occurred: User not found"));
        assertNull(userDTO.getUser());
    }

//    @Test
//    void testDeleteUser_Success() {
//        String email = "john.doe@example.com";
//        User mockUser = new User(1, "John", "Doe", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0812316433", "USER");
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
//        doNothing().when(userRepository).deleteByEmail(email);
//
//        UserDTO userDTO = userManagementService.deleteUser(email);
//
//        assertNotNull(userDTO);
//        assertEquals(200, userDTO.getStatusCode());
//        assertEquals("User deleted successfully", userDTO.getMessage());
//
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(userRepository, times(1)).deleteByEmail(email);
//    }

    @Test
    void testDeleteUser_UserNotFound() {
        String email = "unknown@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserDTO userDTO = userManagementService.deleteUser(email);

        assertNotNull(userDTO);
        assertEquals(404, userDTO.getStatusCode());
        assertEquals("No user found", userDTO.getMessage());

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).deleteByEmail(email);
    }

//    @Test
//    void testUpdateUser_Success() {
//        String email = "john.doe@example.com";
//        User existingUser = new User(1, "John", "Doe", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0812316433", "USER");
//        User updatedUser = new User(1, "Jane", "Smith", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0112316433", "USER");
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
//
//        UserDTO userDTO = userManagementService.updateUser(email, updatedUser);
//
//        assertNotNull(userDTO);
//        assertEquals(200, userDTO.getStatusCode());
//        assertEquals("Updated successfully", userDTO.getMessage());
//        assertNotNull(userDTO.getUser());
//        assertEquals("Jane", userDTO.getUser().getFirstName());
//        assertEquals("Smith", userDTO.getUser().getLastName());
//        assertEquals("0112316433", userDTO.getUser().getMobileNumber());
//
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(userRepository, times(1)).save(existingUser);
//    }

//    @Test
//    void testUpdateUser_UserNotFound() {
//        String email = "unknown@example.com";
//        User updatedUser = new User(1, "Jane", "Smith", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "12345", "0112316433");
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        UserDTO userDTO = userManagementService.updateUser(email, updatedUser);
//
//        assertNotNull(userDTO);
//        assertEquals(404, userDTO.getStatusCode());
//        assertEquals("No user found", userDTO.getMessage());
//        assertNull(userDTO.getUser());
//
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(userRepository, never()).save(any(User.class));
//    }





}
