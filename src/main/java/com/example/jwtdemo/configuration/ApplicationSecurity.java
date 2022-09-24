package com.example.jwtdemo.configuration;

import com.example.jwtdemo.CustomUserService;
import com.example.jwtdemo.jwt.JasonWebTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
@Autowired
CustomUserService customUserService;
@Autowired
JasonWebTokenFilter jasonWebTOkenFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/auth/login/").permitAll()
                .antMatchers("/v2/api-docs","/configuration/**","/swagger*/**","/webjars/**").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(
                (request,response,ex)->{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());

                }
        );
        http.addFilterBefore(jasonWebTOkenFilter,UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public PasswordEncoder getPasswordsEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
