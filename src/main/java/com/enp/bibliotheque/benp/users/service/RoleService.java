package com.enp.bibliotheque.benp.users.service;

import com.enp.bibliotheque.benp.users.entities.Role;
import com.enp.bibliotheque.benp.users.enums.Profile;

import java.util.List;

public interface RoleService {

    void initialize();

    Role findRole(Profile profile);

    List<Role> findAllRoles();
}
