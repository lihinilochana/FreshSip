package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;


public interface UserManagementService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO loginUser(UserDTO userDTO);

    UserDTO refreshToken(UserDTO userDTO);

    UserDTO getAllUser();

    UserDTO getUserByEmail(String email);

    UserDTO deleteUser(String email);

    UserDTO updateUser(String email, User updateUser);


}
