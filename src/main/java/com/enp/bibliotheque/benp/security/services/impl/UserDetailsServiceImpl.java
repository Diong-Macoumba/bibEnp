package com.enp.bibliotheque.benp.security.services.impl;

import com.enp.bibliotheque.benp.persons.entities.User;
import com.enp.bibliotheque.benp.persons.repositories.UserRepository;
import com.enp.bibliotheque.benp.security.entity.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userService) {
        this.userRepository = userService;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");

        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
