package com.example.jwtdemo;

import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= repository.findByEmail(username);

        if(user==null){
            throw  new UsernameNotFoundException("user with "+username+" not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername()
                , user.getPassword(), user.isEnabled(), true,
       true, true,
        new ArrayList<>());
    }
}
