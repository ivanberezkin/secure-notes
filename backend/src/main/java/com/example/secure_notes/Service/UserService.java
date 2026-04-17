package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    UserResponseDto createNewUser(UserRequestDto newUser);

    String verify(UserRequestDto user, HttpServletRequest request);
}
