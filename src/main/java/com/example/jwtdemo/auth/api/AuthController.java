package com.example.jwtdemo.auth.api;


import com.example.jwtdemo.jwt.JwtUtiils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtiils jwtUtiils;
    @PostMapping("api/auth/login/")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request, HttpServletResponse res){
        try {
            Authentication authenticate = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      User authenticatedUser=(User)authenticate.getPrincipal();
      String secret_token= jwtUtiils.generateToken(authenticatedUser);
      AuthResponse response = new AuthResponse(authenticatedUser.getUsername(),secret_token);
     res.addHeader("Authorization",secret_token);


            return  ResponseEntity.ok(response);
        }catch (BadCredentialsException ex){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
