package com.example.jwtdemo.auth.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private  String email;

    private  String token;

}
