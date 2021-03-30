package com.enp.bibliotheque.benp.users.controllers;

import com.enp.bibliotheque.benp.users.entities.User;
import com.enp.bibliotheque.benp.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(UserController.ROUTE_USER)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    public static final String ROUTE_USER = "/enp/users/";

    private UserService userService;

    @PostMapping("create")
    public User create( @RequestBody User user) {
        return userService.create(user);
    }


}
