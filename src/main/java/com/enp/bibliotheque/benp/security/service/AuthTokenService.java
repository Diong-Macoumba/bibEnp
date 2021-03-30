package com.enp.bibliotheque.benp.security.service;

import com.enp.bibliotheque.benp.security.entity.AuthToken;
import com.enp.bibliotheque.benp.users.entities.User;

import java.util.List;

public interface AuthTokenService {

    AuthToken create(User loggedInUser);

    void  update(AuthToken token);

    void delete(AuthToken token);

    boolean isTokenExpired(AuthToken token);

    AuthToken findById( String id);

    List<AuthToken> findByUser( User user);

}
