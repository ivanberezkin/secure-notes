package com.example.secure_notes.Controller;

import com.example.secure_notes.DTO.user.UserRequestDto;
import com.example.secure_notes.DTO.user.UserResponseDto;
import com.example.secure_notes.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserService userService;

    public RegisterController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody UserRequestDto newUser){
        //Todo lägg till check att saker inte är tomma.
        UserResponseDto createdUser = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserRequestDto user, HttpServletRequest request){
        return userService.verify(user, request);
    }

    // TODO: Switch to Spring's built-in logout for proper cookie deletion
    // See: SecurityConfig - use .logout() with .deleteCookies("JSESSIONID")
    // Current implementation works but doesn't set HttpOnly/Secure/SameSite on deletion cookie
    @PostMapping ("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        SecurityContextHolder.clearContext();

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out Successfully");
    }

    @GetMapping("/csrf")
    public ResponseEntity<Void> csrf(CsrfToken csrfToken){
        csrfToken.getToken();
        return ResponseEntity.ok().build();
    }

}
