package com.enp.bibliotheque.benp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.enp.bibliotheque.benp.persons.entities.User;
import com.enp.bibliotheque.benp.persons.repositories.UserRepository;
import com.enp.bibliotheque.benp.security.entity.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.enp.bibliotheque.benp.constants.JwtProperties.*;

public class JWTAutorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository enpUserRepository;

    public JWTAutorizationFilter(AuthenticationManager authenticationManager,UserRepository enpUserRepository) {
        super(authenticationManager);
        this.enpUserRepository = enpUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request,response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthenticationFilter(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);
    }

    private Authentication getUsernamePasswordAuthenticationFilter(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);

        if(header != null) {
            String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build().verify(header.replace(TOKEN_PREFIX, "")).getSubject();
            System.out.println("Username :" + username);

            if(username != null) {
                User user = enpUserRepository.findByUsername(username);
                UserPrincipal userPrincipal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userPrincipal.getAuthorities());
            return authenticationToken;
            }
            return null;
        }
        return null;
    }
}
