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

    @Override
    public boolean registerNewUser(String username, String password) {
        if(username == null || username.isBlank()){
            return false;
        }
        if(password == null || password.isBlank()){
            return false;
        }
        if(userRepository.existByUsername(username)){
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(Roles.USER);

        userRepository.save(newUser);
        return true;
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
