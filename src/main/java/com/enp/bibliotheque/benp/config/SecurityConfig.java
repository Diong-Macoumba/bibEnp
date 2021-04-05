package com.enp.bibliotheque.benp.config;

import com.enp.bibliotheque.benp.security.JwtAuthorizationToken;
import com.enp.bibliotheque.benp.security.services.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.enp.bibliotheque.benp.persons.enums.Profile.ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/**", "/users/**").hasAuthority(ADMIN)
                .antMatchers("/api/agents/**", "/agents/**").hasAuthority(ADMIN)
                .antMatchers("/api/books/**", "/books/**").hasAuthority(ADMIN)
                .anyRequest().authenticated();

        //http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
       // http.addFilter(new JwtAuthorizationToken(this.jwtTokenProvider));
    }
}
