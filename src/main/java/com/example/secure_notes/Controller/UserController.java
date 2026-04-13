package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import com.example.secure_notes.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody UserRequestDto newUser){
        UserResponseDto createdUser = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
