package com.enp.bibliotheque.benp.users.services;

import com.enp.bibliotheque.benp.users.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User create( User user);
    User update(User user, Long id);
    User findByUsername( String username);
    User findUserById( Long id);
    List<User> findAll();
    void delete( Long id);

}
