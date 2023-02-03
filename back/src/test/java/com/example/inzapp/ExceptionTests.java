package com.example.inzapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.inzapp.emotion.EmotionService;
import com.example.inzapp.exceptions.MatchedTrackNotFoundException;
import com.example.inzapp.matchedtrack.MatchedTrack;
import com.example.inzapp.matchedtrack.MatchedTrackController;
import com.example.inzapp.matchedtrack.MatchedTrackRepository;
import com.example.inzapp.matchedtrack.MatchedTrackService;
import com.example.inzapp.track.TrackService;
import com.example.inzapp.user.AppUserRole;
import com.example.inzapp.user.User;
import com.example.inzapp.user.UserRepository;
import com.example.inzapp.user.UserService;
import com.sun.jdi.InternalException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ExceptionTests {


    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchedTrackRepository matchedTrackRepository;

    @Mock
    private Principal principal;

    @Autowired
    private MatchedTrackService matchedTrackService;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void init(){
        this.mvc = MockMvcBuilders
                .webAppContextSetup(applicationContext).apply(springSecurity())
                .build();
    }


    @Test
    public void MatchedTrackNotFoundExceptionTest() throws Exception {

        User testUser = new User(1L,"bence","gadugadu@wp.pl","asdasdaasd",AppUserRole.USER);
        User testUser2 = new User(2L,"bence","gadugadu@wpr.pl","asdasdaasd",AppUserRole.USER);
        createTestUser(testUser);
        createTestUser(testUser2);

        createTestMatchedTrack(1L,testUser);

        Exception exception = assertThrows(MatchedTrackNotFoundException.class, () -> {
            matchedTrackService.deleteMatchingTrack(testUser.getEmail(),1L);
        });

        String expectedMessage = "MatchedTrack of given ID does not exist in user's history";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);

    }




    private void createTestMatchedTrack(Long id, User user) {
        MatchedTrack matchedTrack = new MatchedTrack(id,user,null,null,null,null,null,null,null);
        matchedTrackRepository.save(matchedTrack);
    }

    private void createTestUser(User user) {


        userRepository.save(user);
    }

    private String createToken(User user){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10000 * 60 * 1000))
                .withIssuer("http://localhost:8080/login")
                .withClaim("role",user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }


}
