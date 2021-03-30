package com.enp.bibliotheque.benp.users.repository;

import com.enp.bibliotheque.benp.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRole( String role);
}
