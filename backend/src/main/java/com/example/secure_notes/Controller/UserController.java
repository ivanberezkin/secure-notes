package com.example.secure_notes.Controller;

import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import com.example.secure_notes.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //TODO just for testing rework later!!
    @GetMapping
    public List<UserEntity> getAllUsersTest() {
        return userRepository.findAll();
    }

}