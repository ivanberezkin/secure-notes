package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.user.UserPasswordRequestDto;
import com.example.secure_notes.DTO.user.UserRequestDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import com.example.secure_notes.Exceptions.PasswordUnavailableException;
import com.example.secure_notes.Exceptions.UserAlreadyExistsException;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createNewUser(UserRequestDto newUserDto) {
        if (userRepository.existsByUsername(newUserDto.getUsername())) {
            throw new UserAlreadyExistsException("Username " + newUserDto.getUsername() + "already exists.");
        }

        newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        UserEntity createdUser = userRepository.save(convertToEntity(newUserDto));
        return convertToDto(createdUser);
    }

    @Override
    public String verify(UserRequestDto user, HttpServletRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );
        }
        return "Login Successful";
    }

    @Override
    public UserResponseDto getCurrentUser() {
        return convertToDto(userEntityCurrentUser());
    }

    @Override
    public UserResponseDto changeUserPassword(UserPasswordRequestDto newPasswordDto) {
        UserEntity userEntity = userEntityCurrentUser();
        if (!passwordEncoder.matches(
                newPasswordDto.getCurrentPassword(),
                userEntity.getPassword())) {
            throw new PasswordUnavailableException("UserService: Current Password doesn't match.");
        }
        if (passwordEncoder.matches(
                newPasswordDto.getNewPassword(),
                userEntity.getPassword())) {
            throw new PasswordUnavailableException("UserService: New password must be different.");
        }

        userEntity.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        return convertToDto(userRepository.save(userEntity));
    }

    private UserEntity userEntityCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserService: User not found " + username));
    }

    private UserEntity convertToEntity(UserRequestDto dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    private UserResponseDto convertToDto(UserEntity user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

}
