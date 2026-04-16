package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;

public interface UserService {

    UserResponseDto createNewUser(UserRequestDto newUser);

    String verify(UserRequestDto user);
}
