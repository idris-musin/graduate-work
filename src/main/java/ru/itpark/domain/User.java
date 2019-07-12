package ru.itpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails { // UserDetails определяет набор полей
    private int id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled; // isEnabled
    private boolean accountNonExpired; // isAccountNonExpired
    private boolean accountNonLocked; // isAccountNonLocked
    private boolean credentialsNonExpired; // isCredentialsNonExpired
}
