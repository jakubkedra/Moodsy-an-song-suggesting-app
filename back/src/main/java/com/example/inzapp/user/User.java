package com.example.inzapp.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "User_details") //do testow albo potem zmienic
public class User implements UserDetails  {

    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long Id;
    @Pattern(regexp = "^(?=.{3,16}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$",
            message = "Nazwa użykownika musi zawierać od 3 do 16 znaków i nie zawierać spacji")
    private String login;
    @Column(unique = true)
    @Email
    @NotBlank
    private String email;
    @Length(min = 8)
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole = AppUserRole.USER;
    private Boolean locked;
    private Boolean enabled;

    public User(String login, String email, String password, AppUserRole appUserRole) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.appUserRole = AppUserRole.USER;
        locked = false;
        enabled = true;
    }



    public User(Long id, String login, String email, String password) {
        Id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        locked = false;
        enabled = true;
    }

    public User(Long id, String login, String email, String password, AppUserRole appUserRole) {
        this.Id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.appUserRole = AppUserRole.USER;
        locked = false;
        enabled = true;
    }

    public User(Long id, String login, String email) {
        Id = id;
        this.login = login;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(AppUserRole.USER.name());

        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id.equals(user.Id) && login.equals(user.login) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, login, email);
    }
}
