package com.example.inzapp;

import com.example.inzapp.user.AppUserRole;
import com.example.inzapp.user.User;
import com.example.inzapp.user.UserRepository;
import com.example.inzapp.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserTests {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext).apply(springSecurity())
                .build();
    }

    public User setupUser(){
        User user = new User(1L,"login123","email123@wp.pl","password", AppUserRole.USER);
        user = userRepository.save(user);
        return user;

    }

    @Test
    public void getUserDetailsTest() throws Exception {
        mockMvc.perform(get("/userDetails").with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email123@wp.pl"))
                .andDo(print());
    }


    @Test
    void isHashUnique() {
        // given
        User u1 = new User(2L, "benc", "beeeeee@wp.pl");
        User u2 = new User(3L, "bence", "beeeeee@wp.ple");
        // then
        assertNotEquals(u2.hashCode(), u1.hashCode());

    }
    @Test
    void isNotEquals() {
        // given
        User u1 = new User(2L, "benc", "beeeeee@wp.pl");
        User u2 = new User(3L, "bence", "beeeeee@wp.ple");
        //then
        assertNotEquals(u2, u1);

    }

    @Test
    void isEquals() {
        // given
        User u1 = new User(3L, "bence", "beeeeee@wp.ple");
        User u2 = new User(3L, "bence", "beeeeee@wp.ple");
        //then
        assertEquals(u2, u1);
    }


    @Test
    void isLoginNotNull() {
        // given
        User u1 = new User();
        u1.setLogin("login");
        // then
        assertNotNull(u1.getLogin());

    }

    @Test
    void isEmailNotNull() {
        // given
        User u1 = new User();
        u1.setEmail("email");
        // then
        assertNotNull(u1.getEmail());
    }

    @Test
    void isUserIdNotNull() {
        // given
        User u1 = new User();
        u1.setId(1L);
        // then
        assertNotNull(u1.getId());

    }





}
