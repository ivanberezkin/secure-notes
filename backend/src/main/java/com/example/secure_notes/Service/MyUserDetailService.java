package com.example.secure_notes.Service;

import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Model.UserPrincipal;
import com.example.secure_notes.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("MyUserDetailService: User not found " + username));

        return new UserPrincipal(user);
    }
}
