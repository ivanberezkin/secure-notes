package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody UserRequestDto newUser){
        //Todo lägg till check att saker inte är tomma.
        UserResponseDto createdUser = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }



}
