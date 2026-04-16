package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserResponseDto createNewUser(UserRequestDto newUserDto) {

        newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        UserEntity createdUser = userRepository.save(convertToEntity(newUserDto));
        return convertToDto(createdUser);
    }

    @Override
    public String verify(UserRequestDto user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }


    private UserEntity convertToEntity(UserRequestDto dto){
        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
    private UserResponseDto convertToDto(UserEntity user){
        return UserResponseDto.builder()
                .username(user.getUsername())
                .build();
    }

}
