package com.example.jwtdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Products extends RepresentationModel<Products> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 128)
    @Length(max = 128,min = 5)
    private String name;
    private float price;
}
