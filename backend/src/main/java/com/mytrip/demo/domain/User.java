package com.mytrip.demo.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import java.util.Collection;

@Value
@Builder
public class User implements UserDetails {

    String email;
    String password;
    Collection<? extends Authority> authorities;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

    @Override
    public String getUsername() {
        return email;
    }

    @Value
    @Builder
    public static class Authority implements GrantedAuthority {
        String authority;
    }
}
