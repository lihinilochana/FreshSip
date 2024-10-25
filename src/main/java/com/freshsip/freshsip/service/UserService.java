package com.freshsip.freshsip.service;


import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO getUserByEmail(String u_email){
        User user=userRepository.getUserByEmail(u_email);
        return modelMapper.map(user,UserDTO.class);
    }
}
