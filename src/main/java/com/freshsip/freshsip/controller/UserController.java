package com.freshsip.freshsip.controller;


import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "FreshSip/Order")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserByEmail/{u_email}")
    public UserDTO getUserByEmail(@PathVariable String u_email) {
        return userService.getUserByEmail(u_email);
    }

}
