package com.example.secure_notes.Service;

import com.example.secure_notes.DTO.UserRequestDto;
import com.example.secure_notes.DTO.UserResponseDto;
import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createNewUser(UserRequestDto newUserDto) {
        UserEntity createdUser = userRepository.save(convertToEntity(newUserDto));
        return convertToDto(createdUser);
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
