package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.Exceptions.UserAlreadyExistsException;
import com.example.secure_notes.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createNewUser_shouldThrowException_whenUsernameExists() {
        when(userRepository.existsByUsername("ivan")).thenReturn(true);

        UserRequestDto testUser = new UserRequestDto("ivan", "12345678");

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createNewUser(testUser);
        });
    }
}