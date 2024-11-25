package com.freshsip.freshsip.dto;


import com.freshsip.freshsip.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String firstName;
    private String lastName;
    private String nic;
    private LocalDate dob;
    private String email;
    private String password;
    private String confirmPassword;
    private String mobileNumber;
    private String userType;
    private User user;
    private List<User> userList;
}
