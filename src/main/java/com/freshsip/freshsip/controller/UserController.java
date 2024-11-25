package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "FreshSip/")

public class UserController {

    @Autowired
    private UserManagementService userManagementService;


    @PostMapping("/adminUser/user")
    public ResponseEntity<UserDTO> saveUser (@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userManagementService.saveUser(userDTO));
    }

    @PostMapping("/adminUser/login")
    public ResponseEntity<UserDTO>loginUser(@RequestBody UserDTO userDTO){
        return  ResponseEntity.ok(userManagementService.loginUser(userDTO));
    }

    @PostMapping("/admin/refreshToken")
    public ResponseEntity<UserDTO> refreshToken (@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userManagementService.refreshToken(userDTO));
    }

    @GetMapping("/admin/users")
    public ResponseEntity<UserDTO> getAllUser (){
        return ResponseEntity.ok(userManagementService.getAllUser());
    }

    @GetMapping("/adminUser/{email}")
    public ResponseEntity<UserDTO> getUserByEmail (@PathVariable String email){
        return ResponseEntity.ok(userManagementService.getUserByEmail(email));
    }

    @PutMapping("/adminUser/{email}")
    public ResponseEntity<UserDTO> updateUser (@PathVariable String email, @RequestBody User userDTO){
        return ResponseEntity.ok(userManagementService.updateUser(email, userDTO));
    }



    @DeleteMapping("/adminUser/{email}")
    public ResponseEntity<UserDTO> deleteUser (@PathVariable String email){
        return ResponseEntity.ok(userManagementService.deleteUser(email));
    }



}
