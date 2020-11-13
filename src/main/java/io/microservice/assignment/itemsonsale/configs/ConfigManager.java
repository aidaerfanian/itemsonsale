/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.configs;

import io.microservice.assignment.itemsonsale.UsersService;
import io.microservice.assignment.itemsonsale.filters.RequestsFilter;
import io.microservice.assignment.itemsonsale.utils.Helper;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author aida.erfanian
 */
@EnableWebSecurity
public class ConfigManager extends WebSecurityConfigurerAdapter {
    
    private final String RBC_SHOPPING_DOMAIN_NAME = "shopping.rbc.com";
    private String rbcShoppingIpAddress = "127.0.0.1";
    
    @Autowired 
    private Helper helper;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private RequestsFilter requestsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(usersService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            this.rbcShoppingIpAddress = helper.getIPAddressOfDomain(RBC_SHOPPING_DOMAIN_NAME);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/").hasIpAddress(rbcShoppingIpAddress)
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // No session
        
        http.addFilterBefore(requestsFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
