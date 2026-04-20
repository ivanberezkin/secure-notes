package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.user.UserRequestDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import com.example.secure_notes.Exceptions.UserAlreadyExistsException;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Utils.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

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

    @Test
    void createNewUser_shouldSaveUser_whenUserDoesntExist(){
        when(userRepository.existsByUsername("ivan")).thenReturn(false);

        UserEntity savedUser = new UserEntity(1L, "ivan", "hashedPassword", Roles.USER, null);

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserRequestDto testUser = new UserRequestDto("ivan", "12345678");
        UserResponseDto result = userService.createNewUser(testUser);
        assertEquals("ivan", result.getUsername());
        verify(userRepository).save(any(UserEntity.class));
    }


}