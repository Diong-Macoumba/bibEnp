package com.enp.bibliotheque.benp.users.repository;

import com.enp.bibliotheque.benp.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername( String username);
}