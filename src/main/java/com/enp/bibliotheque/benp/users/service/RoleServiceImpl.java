package com.enp.bibliotheque.benp.users.service;

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
    public void initialize() {
    if( roleRepository.count() > 0) {
        return;
    }
    for (Profile profile: Profile.values()) {
        Role role = new Role();
        role.setRole(profile.name());
        roleRepository.save(role);
    }

    roleRepository.flush();
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
