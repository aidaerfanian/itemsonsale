/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale;

import io.microservice.assignment.itemsonsale.entities.Items;
import io.microservice.assignment.itemsonsale.repositories.OrdersRepository;
import io.microservice.assignment.itemsonsale.security.TokenManager;
import io.microservice.assignment.itemsonsale.types.AuthenticationRequest;
import io.microservice.assignment.itemsonsale.types.AuthenticationResponse;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author aida.erfanian
 */
@RestController
public class RequestsController {
    
    @Autowired 
    private OrdersRepository ordersRepository;

    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private TokenManager tokenManager;
    
    @RequestMapping("/welcome")
    public String hello() {
        return "Welcome to Items On Sale API!";
    }
    
    /**
     *
     * @param id
     * @return a HashSet of recommended items
     */
    @GetMapping("/recommendations/{id}")
    public LinkedHashSet<Items> recommendations(@PathVariable String id) {
        int userId = Integer.parseInt(id);

        LinkedHashSet<Items> itemsOfFavoriteCategories = ordersRepository.getItemsOfFavoriteCategories(userId);
        LinkedHashSet<Items> highestRankedOnSaleItems = ordersRepository.getHighestRankedOnSaleItems();
        
        LinkedHashSet<Items> recommendedItems = new LinkedHashSet<>(itemsOfFavoriteCategories);
        recommendedItems.addAll(highestRankedOnSaleItems);
        
        return recommendedItems;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST) 
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException error) {
            throw new Exception("Incorrect username and/or password", error);
        }
        
        final UserDetails userDetails = usersService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenManager.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
