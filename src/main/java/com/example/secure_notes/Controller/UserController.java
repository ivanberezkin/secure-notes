package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import com.example.secure_notes.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody UserRequestDto newUser){
        UserResponseDto createdUser = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
