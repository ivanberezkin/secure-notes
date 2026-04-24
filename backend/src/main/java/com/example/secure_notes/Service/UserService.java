package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.user.UserRequestDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserResponseDto createNewUser(UserRequestDto newUser);

    String verify(UserRequestDto user, HttpServletRequest request);

    UserResponseDto getCurrentUser();

}
