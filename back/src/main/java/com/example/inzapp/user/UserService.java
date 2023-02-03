package com.example.inzapp.user;

import com.example.inzapp.registration.token.ConfirmationToken;
import com.example.inzapp.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        } else {
            List<SimpleGrantedAuthority> listAuthorities = new ArrayList<>();
            listAuthorities.add(new SimpleGrantedAuthority(AppUserRole.USER.name()));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), listAuthorities);
        }

    }

    public User getUser(String email){
        return userRepository.findByEmail(email).get();
    }

    public User getUserById(Long id){


        return userRepository.findById(id).get();
    }


    public void signUpUser(User user){

        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);



    }

    public void enableUser(String email){

        User u = (User) loadUserByUsername(email);

        if(u == null){
            throw new IllegalStateException("user Not found");
        }
        u.setEnabled(true);

    }


}
