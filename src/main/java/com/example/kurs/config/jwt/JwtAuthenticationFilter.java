package com.example.kurs.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        {

            // Получаем токен из заголовка Authorization
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7); // "Bearer " длинна 7

                if (jwtProvider.validateJwtToken(jwtToken)) {
                    String username = jwtProvider.getUserNameFromJwtToken(jwtToken);
                    String role = jwtProvider.getRoleFromJwtToken(jwtToken);
                    String code = jwtProvider.getCodeFromJwtToken(jwtToken);
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                    CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(username, Collections.singleton(authority),code);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        }
    }
}
