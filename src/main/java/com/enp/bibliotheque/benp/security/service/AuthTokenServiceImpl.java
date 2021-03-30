package com.enp.bibliotheque.benp.security.service;

import com.enp.bibliotheque.benp.constants.NumberConstant;
import com.enp.bibliotheque.benp.security.entity.AuthToken;
import com.enp.bibliotheque.benp.security.repository.AuthTokenRepository;
import com.enp.bibliotheque.benp.users.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class AuthTokenServiceImpl implements AuthTokenService {

    private AuthTokenRepository tokenRepository;

    public AuthTokenServiceImpl(AuthTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public AuthToken create(User loggedInUser) {

        AuthToken token = new AuthToken();
        token.setUser(loggedInUser);
        tokenRepository.saveAndFlush(token);
        return token;
    }

    @Override
    public void update(AuthToken token) {
        token.setLastAccessToken(LocalDateTime.now());
    }

    @Override
    public void delete(AuthToken token) {
        tokenRepository.delete(token);
    }

    @Override
    public boolean isTokenExpired(AuthToken token) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastAccessToken = token.getLastAccessToken();

        return ChronoUnit.DAYS.between(now, lastAccessToken) > NumberConstant.MAX_NUMBER_TOKEN;
    }

    @Override
    public AuthToken findById(String id) {

        return tokenRepository.findById(id).orElse(null);
    }

    @Override
    public List<AuthToken> findByUser(User user) {

        return tokenRepository.findByUser(user);
    }
}
