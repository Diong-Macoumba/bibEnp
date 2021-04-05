package com.enp.bibliotheque.benp.persons.repositories;

import com.enp.bibliotheque.benp.persons.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById( Long id);
    User findByUsername( String username);
}
