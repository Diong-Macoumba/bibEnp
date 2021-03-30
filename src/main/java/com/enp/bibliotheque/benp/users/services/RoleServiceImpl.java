package com.enp.bibliotheque.benp.users.services;

import com.enp.bibliotheque.benp.users.entities.Role;
import com.enp.bibliotheque.benp.users.enums.Profile;
import com.enp.bibliotheque.benp.users.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role create(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role findRole(Profile profile) {
        return roleRepository.findRoleByRole(profile.name());
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
