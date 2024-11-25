package com.freshsip.freshsip.dto;

import com.freshsip.freshsip.entity.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void testGetterSetter() {

        UserDTO userDTO = new UserDTO();

        userDTO.setStatusCode(200);
        userDTO.setError("No error");
        userDTO.setMessage("Success");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setNic("123456789V");
        userDTO.setDob(LocalDate.of(1990, 1, 1));
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");
        userDTO.setMobileNumber("1234567890");
        userDTO.setUserType("Admin");

        assertEquals(200, userDTO.getStatusCode());
        assertEquals("No error", userDTO.getError());
        assertEquals("Success", userDTO.getMessage());
        assertEquals("John", userDTO.getFirstName());
        assertEquals("Doe", userDTO.getLastName());
        assertEquals("123456789V", userDTO.getNic());
        assertEquals(LocalDate.of(1990, 1, 1), userDTO.getDob());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertEquals("password123", userDTO.getPassword());
        assertEquals("password123", userDTO.getConfirmPassword());
        assertEquals("1234567890", userDTO.getMobileNumber());
        assertEquals("Admin", userDTO.getUserType());
    }

//    @Test
//    void testListOfUsers() {
//        // Create sample users
//        User user1 = new User(1, "John", "Doe", "200150103490", (LocalDate.of(2000, 11, 24)), "john.doe@example.com", "12345", "0812316433", "USER");
//        User user2 = new User(2, "Jane", "Doe", "198712587955", (LocalDate.of(1987,8,22)), "jane.doe@example.com", "654321", "011654321", "ADMIN");
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserList(List.of(user1, user2));
//
//        assertNotNull(userDTO.getUserList());
//        assertEquals(2, userDTO.getUserList().size());
//        assertEquals("John", userDTO.getUserList().get(0).getFirstName());
//        assertEquals("Jane", userDTO.getUserList().get(1).getFirstName());
//    }

    @Test
    void testDefaultConstructor() {
        UserDTO userDTO = new UserDTO();

        assertNull(userDTO.getError());
        assertNull(userDTO.getMessage());
        assertNull(userDTO.getFirstName());
        assertNull(userDTO.getLastName());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getNic());
        assertNull(userDTO.getPassword());
        assertNull(userDTO.getConfirmPassword());
        assertNull(userDTO.getMobileNumber());
        assertNull(userDTO.getUserType());
        assertNull(userDTO.getUser());
        assertNull(userDTO.getUserList());
        assertEquals(0, userDTO.getStatusCode());
    }

    @Test
    void testAllArgsConstructor() {
        UserDTO userDTO = new UserDTO(200, null, "Success", null, null, null, "John", "Doe", "200150103490",
                LocalDate.of(2000, 11, 24), "john.doe@example.com", "12345", "12345", "0812316433", "USER", null, null);

        assertEquals(200, userDTO.getStatusCode());
        assertEquals("Success", userDTO.getMessage());
        assertEquals("John", userDTO.getFirstName());
        assertEquals("Doe", userDTO.getLastName());
        assertEquals("200150103490", userDTO.getNic());
        assertEquals(LocalDate.of(2000, 11, 24), userDTO.getDob());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertEquals("12345", userDTO.getPassword());
        assertEquals("12345", userDTO.getConfirmPassword());
        assertEquals("0812316433", userDTO.getMobileNumber());
        assertEquals("USER", userDTO.getUserType());
    }

}