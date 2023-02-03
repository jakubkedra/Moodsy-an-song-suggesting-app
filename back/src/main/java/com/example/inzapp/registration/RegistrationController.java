package com.example.inzapp.registration;

import com.example.inzapp.user.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody UserDto userDto){
         registrationService.register(userDto);
    }

    /*
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
    */


}