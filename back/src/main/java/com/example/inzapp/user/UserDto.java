package com.example.inzapp.user;


import com.example.inzapp.email.ValidEmail;
import com.example.inzapp.user.AppUserRole;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class UserDto {


    public UserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserDto(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }


    public UserDto(String login, String email, String password, AppUserRole appUserRole) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    private Long id;
    private  String login;
    @ValidEmail
    @NotNull
    @NotEmpty
    private  String email;
    private  String password;
    private AppUserRole appUserRole;

}
