package com.enp.bibliotheque.benp.persons.services;

import com.enp.bibliotheque.benp.persons.entities.User;

import java.util.List;

public interface UserService {

    User create( User user);
    User update( User user, Long id);
    void delete( Long id);
    User find( Long id);
    List<User> findAll();
    List<User> findAllByUsername( String username);
    void addRoleToUser(String username, String role);
}
