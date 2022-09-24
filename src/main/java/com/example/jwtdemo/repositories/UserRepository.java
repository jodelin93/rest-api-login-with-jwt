package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
