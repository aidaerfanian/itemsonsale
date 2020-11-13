/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.filters;

import io.microservice.assignment.itemsonsale.UsersService;
import io.microservice.assignment.itemsonsale.security.TokenManager;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

/**
 *
 * @author aida.erfanian
 */
@Component
public class RequestsFilter extends OncePerRequestFilter{

    private final String BEARER_TOKEN_HEAD = "Bearer ";

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private TokenManager tokenManager;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException, ExpiredJwtException {
        String username = null;
        String token = null;
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        if(authorizationHeader != null && authorizationHeader.contains(BEARER_TOKEN_HEAD)) {
            token = authorizationHeader.substring(BEARER_TOKEN_HEAD.length());
            username = tokenManager.extractUsername(token);
        }
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = this.usersService.loadUserByUsername(username);
            
            if(tokenManager.validateToken(token, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                logger.error("Invalid Token!");
            }
        }
        
        chain.doFilter(request, response);
        
    }
}
