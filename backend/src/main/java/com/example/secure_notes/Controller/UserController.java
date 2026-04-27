package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.user.UserPasswordRequestDto;
import com.example.secure_notes.DTO.user.UserRequestDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Service.NoteService;
import com.example.secure_notes.Service.NoteServiceImpl;
import com.example.secure_notes.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(){
        UserResponseDto currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/changepass")
    public ResponseEntity<UserResponseDto> changeUserPassword(
            @Valid @RequestBody UserPasswordRequestDto newPassword
            ){
        UserResponseDto user = userService.changeUserPassword(newPassword);
        return ResponseEntity.ok(user);
    }

}