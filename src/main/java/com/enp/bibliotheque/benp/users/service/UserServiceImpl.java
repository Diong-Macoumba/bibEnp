package com.enp.bibliotheque.benp.users.service;

import com.enp.bibliotheque.benp.exception.InvalidPasswordException;
import com.enp.bibliotheque.benp.users.entities.User;
import com.enp.bibliotheque.benp.users.enums.Profile;
import com.enp.bibliotheque.benp.users.repository.RoleRepository;
import com.enp.bibliotheque.benp.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User create(User user) {
        if (user == null) {
            if (!user.getPassword().equals(user.getRepassword()))
                throw new InvalidPasswordException("le mot de passe n'est pas identique ");

            User u = new User();
            u.setFirstname(user.getFirstname());
            u.setUsername(user.getUsername());
            u.setLastname(user.getLastname());
            u.setEmail(user.getEmail());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setPhoneNumber(user.getPhoneNumber());
            u.setGender(user.getGender());
            u.setActive(user.getActive());
            userRepository.save(u);
            addRoleToUser(user.getUsername(), Profile.USER.name());
            return u;
        } else {
            throw new UsernameNotFoundException("l'utilisateur existe 'déja "+ user.getUsername());
        }
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findUserByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String role) {

        User user = userRepository.findUserByUsername(username);
        user.setAuthorities(List.of(roleRepository.findRoleByRole(role)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if(user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with username :" + username);
    }
}
