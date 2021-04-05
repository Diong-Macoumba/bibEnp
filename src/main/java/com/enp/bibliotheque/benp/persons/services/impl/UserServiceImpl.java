package com.enp.bibliotheque.benp.persons.services.impl;

import com.enp.bibliotheque.benp.exceptions.InvalidPasswordException;
import com.enp.bibliotheque.benp.exceptions.UserAlreadyExistsException;
import com.enp.bibliotheque.benp.persons.entities.Role;
import com.enp.bibliotheque.benp.persons.entities.User;
import com.enp.bibliotheque.benp.persons.repositories.RoleRepository;
import com.enp.bibliotheque.benp.persons.repositories.UserRepository;
import com.enp.bibliotheque.benp.persons.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.enp.bibliotheque.benp.persons.enums.Profile.USER;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User user) {

        User util = userRepository.findByUsername(user.getUsername());

        if(util == null) {
            if(!user.getPassword().equals(user.getRepassword())) {
                throw new InvalidPasswordException("le mot de passe n'est identique");
            }
            User u = new User();
            u.setFirstname(user.getFirstname());
            u.setLastname(user.getLastname());
            u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setGender(user.getGender());
            u.setPhoneNumber(user.getPhoneNumber());
            u.setActive(user.getActive());
            userRepository.save(u);

            addRoleToUser(u.getUsername(), USER);
            return u;
        }else {
            throw new UserAlreadyExistsException("L'utilisateur existe dejà");
        }

    }

    @Override
    public User update(User u, Long id) {

        User util = find(id);

        if(util == null) {
            throw new RuntimeException("L'utilisateur n'existe pas");
        }

        User user = userRepository.findByUsername(u.getUsername());

        if( user != null && !user.getUsername().equals(util.getUsername())) {
            throw new RuntimeException("Le username est utilisé");
        }

        String firstname = u.getFirstname();
        if (!firstname.isBlank()){
            util.setFirstname(u.getFirstname());
        }

        String lastname = u.getLastname();
        if (!lastname.isBlank()){
            util.setLastname(u.getLastname());
        }
        String username = u.getUsername();
        if (!username.isBlank()){
            util.setUsername(u.getUsername());
        }
        String email = u.getEmail();
        if (!email.isBlank()){
            util.setEmail(u.getEmail());
        }
        String password = u.getPassword();
        if (!password.isBlank()) {
            util.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        }
        String gender = u.getGender();
        if (!gender.isBlank()){
            util.setGender(u.getGender());
        }
        String phoneNumber = u.getPhoneNumber();
        if (!phoneNumber.isBlank()){
            util.setPhoneNumber(u.getPhoneNumber());
        }
        return util;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User find(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByUsername(String username) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String r) {
    User user = userRepository.findByUsername(username);
    Role role = roleRepository.findByRole(r);
    user.getRoles().add(role);
    }
}
