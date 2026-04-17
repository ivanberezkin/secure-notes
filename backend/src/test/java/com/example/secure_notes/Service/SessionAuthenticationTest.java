package com.example.secure_notes.Service;

import com.example.secure_notes.Model.UserEntity;
import com.example.secure_notes.Repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        UserEntity user = UserEntity.builder()
                .username("testuser")
                .password(passwordEncoder.encode("12345678"))
                .build();
        userRepository.save(user);
    }

    @Test
    public void authenticatedRequest_shouldSucceed_withSessionCookie() throws Exception {

        MockHttpSession session = new MockHttpSession();
        loginUser(session);

        mockMvc.perform(get("/api/users")
                        .session(session))
                .andExpect(status().isOk());
    }

    @Test
    public void requestApiUsersWithoutAuthentication_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isForbidden()).andReturn();
    }

    @Test
    public void authenticatedRequestLogout_TryingToAccessApiUsers_shouldReturn403() throws Exception {
        MockHttpSession session = new MockHttpSession();
        Cookie csrfCookie = loginUser(session);

        mockMvc.perform(post("/logout")
                .session(session)
                .cookie(csrfCookie)
                .header("X-XSRF-TOKEN", csrfCookie.getValue()));
        assertTrue(session.isInvalid());

        mockMvc.perform(get("/api/users")
                        .session(session))
                .andExpect(status().isForbidden()).andReturn();

    }

    private Cookie loginUser(MockHttpSession session) throws Exception {
        MvcResult csrfResult = mockMvc.perform(post("/login")
                        .session(session))
                .andReturn();

        Cookie csrfCookie = csrfResult.getResponse().getCookie("XSRF-TOKEN");
        assertNotNull(csrfCookie, "XSRF-TOKEN present");
        String csrfToken = csrfCookie.getValue();

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .cookie(csrfCookie)
                        .header("X-XSRF-TOKEN", csrfToken)
                        .content("""
                                {
                                    "username": "testuser",
                                    "password": "12345678"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn();

        return csrfCookie;
    }

}
