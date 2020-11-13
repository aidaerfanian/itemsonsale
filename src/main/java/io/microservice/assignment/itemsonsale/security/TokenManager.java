/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author aida.erfanian
 */
@Service
public class TokenManager {
  private final String SECRET = "thisisasecret";
  private final int TWO_HOURS = 2 * 3600 * 1000;
  
  private Claims extractAllClaims(String token) {
      return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
  }
  private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
  }
  
  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(TWO_HOURS + System.currentTimeMillis()))
              .signWith(SignatureAlgorithm.HS256, SECRET).compact();
  }

  public String generateToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, userDetails.getUsername());
  }
  
  public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
  }
  
  public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
  }
  
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }
  
  public Boolean validateToken(String token, UserDetails userDetails) {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
