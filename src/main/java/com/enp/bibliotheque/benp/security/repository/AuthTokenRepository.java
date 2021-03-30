package com.enp.bibliotheque.benp.security.repository;

import com.enp.bibliotheque.benp.security.entity.AuthToken;
import com.enp.bibliotheque.benp.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
    List<AuthToken> findByUser(User user);
}
