package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
}
