package com.enp.bibliotheque.benp.persons.controllers;

import com.enp.bibliotheque.benp.persons.entities.User;
import com.enp.bibliotheque.benp.persons.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }


    @RequestMapping(method = RequestMethod.GET, value = "all")
    public List<User> findAll() {
        return userService.findAll();
    }




}
