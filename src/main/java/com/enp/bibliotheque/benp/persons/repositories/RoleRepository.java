package com.enp.bibliotheque.benp.persons.repositories;

import com.enp.bibliotheque.benp.persons.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole( String role);
}
