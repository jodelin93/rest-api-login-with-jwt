package com.example.jwtdemo.controllers;

import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User tempUser= new User();
        BeanUtils.copyProperties(user,tempUser);
        tempUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(tempUser);
        URI uriUser= URI.create("/users/"+savedUser.getId());

        return  ResponseEntity.created(uriUser).body(savedUser);
    }
}
