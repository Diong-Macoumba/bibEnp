package com.enp.bibliotheque.benp.users.controllers;

import com.enp.bibliotheque.benp.users.entities.User;
import com.enp.bibliotheque.benp.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/", produces = "application/json")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    public static final String ROUTE_USER = "/enp/users/";

    private UserService userService;

    @PostMapping("create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> create( @RequestBody User user) {

        try {
           User u = userService.create(user);
           return ResponseEntity.of(Optional.of(u));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> update( @RequestBody User user,@PathVariable("id") Long id ) {
        return null;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> findAll() {

        List<User> users = userService.findAll();
        if (users.size() <= 0 ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(users));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> find(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }

    @DeleteMapping("{id]")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

