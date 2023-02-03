package com.example.inzapp;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.inzapp.matchedtrack.MatchedTrack;
import com.example.inzapp.matchedtrack.MatchedTrackRepository;
import com.example.inzapp.user.AppUserRole;
import com.example.inzapp.user.User;
import com.example.inzapp.user.UserRepository;
import com.example.inzapp.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Collectors;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class MatchedTrackTests {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchedTrackRepository matchedTrackRepository;

    @Autowired
    private UserService userService;

    private final User testUser = new User(1L,"bence","gadugadu@wp.pl","asdasdaasd",AppUserRole.USER);


    @BeforeEach
    public void init(){
        this.mvc = MockMvcBuilders
                .webAppContextSetup(applicationContext).apply(springSecurity())
                .build();
    }


    @BeforeEach
    public void setup(){
        createTestUser(testUser);
        createTestMatchedTrack(1L,testUser);

    }


    @Test
    public void deleteMatchedTrackTest() throws Exception {

        System.out.println(createToken(testUser));

        mvc.perform(delete("/api/deleteTrack/1").header("Authorization", "Bearer " + createToken(testUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getMatchedTracksWithoutAuthTest() throws Exception{


        mvc.perform(get("/api/getMatchedTracks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isForbidden());
    }




    @Test
    public void getMatchedTrackTest() throws Exception {

        mvc.perform(get("/api/getMatchedTracks").header("Authorization", "Bearer " + createToken(testUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("testTrack"));
    }


    @Test
    public void saveMatchedTrackTest() throws Exception{

        String genres = "metal";
        final byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\lajse\\Desktop\\InzApp\\output.jpg"));
        mvc.perform(multipart("/api/saveTrack")
                        .file("image", bytes).param("genres",genres).header("Authorization", "Bearer " + createToken(testUser)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    private void createTestMatchedTrack(Long id, User user) {
        MatchedTrack matchedTrack = new MatchedTrack(id,user,"testTrack",null,null,null,null,null,null);
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
