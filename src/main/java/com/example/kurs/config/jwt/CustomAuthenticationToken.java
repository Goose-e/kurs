package com.example.kurs.config.jwt;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private String username;
    @Getter
    private String code;
    public CustomAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities,String code) {
        super(authorities);
        this.username = username;
        this.code = code;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;  // Credentials are not needed for this token
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
