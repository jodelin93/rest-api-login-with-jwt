package com.example.jwtdemo.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtiils {
    private static final long EXPIRE_DURATION=24 * 60 * 60 * 1000;
    private static final String SECRET_KEY="i am who i am till then end of this world";

    public String generateToken(User user){

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer("Jodelin application APi")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();

    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return  true;
        }catch (Exception ex){
            return  false;
        }
    }
    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }
    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}
