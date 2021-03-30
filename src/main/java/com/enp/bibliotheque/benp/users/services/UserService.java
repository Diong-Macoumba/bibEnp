package com.enp.bibliotheque.benp.users.services;

import com.enp.bibliotheque.benp.users.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User create( User user);
    User update(User user, Long id);
    User findByUsername( String username);

}
