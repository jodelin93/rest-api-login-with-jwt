package com.example.jwtdemo.jwt;

import com.example.jwtdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JasonWebTokenFilter extends OncePerRequestFilter {
    @Autowired JwtUtiils jwtUtiils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if(!hasAuthorizationHeader(request)) {
        filterChain.doFilter(request, response);
        return;
    }
    String accessToken = getAccessToken(request);

    if(!jwtUtiils.validateToken(accessToken)){
        filterChain.doFilter(request, response);
        return;
    }

    setAuthenticationContext(accessToken,request);
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(accessToken);
        UsernamePasswordAuthenticationToken token=
                new UsernamePasswordAuthenticationToken(userDetails,null,null);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private UserDetails getUserDetails(String accessToken) {
        User user = new User();
       String token= jwtUtiils.getSubject(accessToken);
       user.setEmail(token);
       return user;
    }

    public boolean hasAuthorizationHeader(HttpServletRequest request){
        String token= request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(token)) return  false;
        return true;
    }

    public String getAccessToken(HttpServletRequest request){
       return  request.getHeader("Authorization");
    }
}
