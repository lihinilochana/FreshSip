package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO response = new UserDTO();

        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());
        try {
            if (userOptional.isEmpty()) {
                if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    User newUser = modelMapper.map(userDTO, User.class);
                    userRepository.save(newUser);
                    response.setStatusCode(200);
                    response.setMessage("User save successfully.");

                } else {
                    response.setMessage("Your confirm password not match with your password.");

                }
            } else {
                response.setMessage("You have already used your email to sign in. Please use different email.");

            }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO){
        UserDTO response = new UserDTO();
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
            var user = userRepository.findByEmail(userDTO.getEmail()). orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setEmail(user.getEmail());
            response.setUserType(user.getUserType());
            response.setRefreshToken(jwt);
            response.setExpirationTime("24Hrs");
            response.setMessage("Login Success");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public UserDTO refreshToken(UserDTO userDTO){
        UserDTO response = new UserDTO();

        try{
            String email = jwtUtils.extractUserName(userDTO.getToken());
            User user = userRepository.findByEmail(email).orElseThrow();
            if (jwtUtils.isTokenValid(userDTO.getToken(), user)){
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(userDTO.getToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Refresh Token");
            }

            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @Override
    public UserDTO getAllUser(){
        UserDTO userDTO = new UserDTO();

        try{
            List<User> result = userRepository.findAll();
            if(!result.isEmpty()){
                userDTO.setUserList(result);
                userDTO.setStatusCode(200);
                userDTO.setMessage("Successful");
            }
            else{
                userDTO.setStatusCode(404);
                userDTO.setMessage("No user found");
            }
            return userDTO;
        }

        catch (Exception e){
            userDTO.setStatusCode(500);
            userDTO.setMessage("Error occurred: " + e.getMessage());
            return userDTO;
        }

    }

    @Override
    public UserDTO getUserByEmail(String email){
        UserDTO userDTO = new UserDTO();

        try{
            User userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            userDTO.setUser(userByEmail);
            userDTO.setStatusCode(200);
            userDTO.setMessage("User with ID " + email + " found successfully.");
        }

        catch (Exception e){
            userDTO.setStatusCode(500);
            userDTO.setMessage("Error occurred: " + e.getMessage());
        }

        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO deleteUser (String email){
        UserDTO userDTO = new UserDTO();

        try{
            Optional<User>userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()){
                userRepository.deleteByEmail(email);
                userDTO.setStatusCode(200);
                userDTO.setMessage("User deleted successfully");
            }

            else{
                userDTO.setStatusCode(404);
                userDTO.setMessage("No user found");
            }
        }

        catch (Exception e){
            userDTO.setStatusCode(500);
            userDTO.setMessage("Error occurred: " + e.getMessage());
        }

        return userDTO;
    }

    @Override
    public UserDTO updateUser(String email, User updateUser){
        UserDTO userDTO = new UserDTO();

        try{
            Optional<User>userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()){
                User existingUser = userOptional.get();
                existingUser.setFirstName(updateUser.getFirstName());
                existingUser.setLastName(updateUser.getLastName());
                existingUser.setMobileNumber(updateUser.getMobileNumber());

                User saveUser = userRepository.save(existingUser);
                userDTO.setUser(saveUser);
                userDTO.setStatusCode(200);
                userDTO.setMessage("Updated successfully");
            }

            else{
                userDTO.setStatusCode(404);
                userDTO.setMessage("No user found");
            }
        }

        catch (Exception e){
            userDTO.setStatusCode(500);
            userDTO.setMessage("Error occurred: " + e.getMessage());
        }

        return userDTO;
    }


}
