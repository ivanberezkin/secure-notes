package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto createNewUser(UserRequestDto newUser) {
        return null;
    }
}
