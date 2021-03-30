package com.enp.bibliotheque.benp.users.services;

import com.enp.bibliotheque.benp.users.entities.Role;
import com.enp.bibliotheque.benp.users.enums.Profile;

import java.util.List;

public interface RoleService {

    Role create( Role role);

    Role findRole(Profile profile);

    List<Role> findAllRoles();
}
