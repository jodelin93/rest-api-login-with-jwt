package com.example.jwtdemo;

import com.example.jwtdemo.entities.Products;
import com.example.jwtdemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtdemoApplication {
	@Autowired
	ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}
//	@PostConstruct
//	public void init(){
//		Products products1= new Products();
//		products1.setName("Robusto");
//		products1.setPrice(23.4f);
//		Products products2= new Products();
//		products2.setName("Tampico");
//		products2.setPrice(14.4f);
//		Products products3= new Products();
//		products3.setName("Malta");
//		products3.setPrice(30.4f);
//		List<Products> productsList = new ArrayList<>();
//		 productsList.add(products1);
//		productsList.add(products2);
//		productsList.add(products3);
//		productsList.stream().forEach(prod->productRepository.save(prod));
//
//	}

}
