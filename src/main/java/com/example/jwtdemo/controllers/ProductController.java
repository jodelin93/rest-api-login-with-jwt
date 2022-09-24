package com.example.jwtdemo.controllers;

import com.example.jwtdemo.configuration.ObjectMapperUtils;
import com.example.jwtdemo.entities.Products;
import com.example.jwtdemo.exception.ProductException;
import com.example.jwtdemo.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public CollectionModel<ProductResponse> getAllProducts(){
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getAllProducts()).withSelfRel();
       // List<Products> productsList =productRepository.findAll();
        List<ProductResponse> productResponse = ObjectMapperUtils.mapAll(productRepository.findAll(), ProductResponse.class);



//        productsList.stream().forEach(products -> {
//            ProductResponse response = new ProductResponse(products.getName(),products.getPrice());
//            productResponse.add(response);
//        });

        return CollectionModel.of(productResponse,Arrays.asList(selfLink));
    }
    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products products){

        Products prod=productRepository.save(products);
        URI produri=URI.create("/products/"+prod.getId());
        return  ResponseEntity.created(produri).body(prod);

    }
    @GetMapping("/{id}")
    public EntityModel<ProductResponse> getProduct(@PathVariable Long id) throws Exception {


        Link oneProductLink= WebMvcLinkBuilder.linkTo(ProductController.class)
                .slash(id).withRel("product");
        Link allproductLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getAllProducts())
                .withRel("all product");
        Optional<Products> prod= productRepository.findById(id);
//        prod.get().add(oneProductLink);
//        prod.get().add(allproductLink);

        if(!prod.isPresent()){
            throw new ProductException("product with "+id+" not found");
        }

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(prod.get(),productResponse);

        return  EntityModel.of(productResponse, Arrays.asList(oneProductLink,allproductLink));
    }
}
