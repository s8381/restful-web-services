package com.in28minutes.rest.webservices.restfulwebservices.socialmediaapplication;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService dao;

    public UserResource(UserDaoService dao){
        this.dao = dao;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return dao.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUserWithId(@PathVariable Integer id){
        User user = dao.findWithId(id);

        if(user == null){
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserWithId(@PathVariable Integer id){
        dao.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = dao.createUser(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
