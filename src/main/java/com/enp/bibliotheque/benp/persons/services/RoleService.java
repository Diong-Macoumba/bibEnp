package com.enp.bibliotheque.benp.persons.services;

import com.enp.bibliotheque.benp.persons.entities.Role;

public interface RoleService {

    Role create(String role);
    void delete( Long id);
}
