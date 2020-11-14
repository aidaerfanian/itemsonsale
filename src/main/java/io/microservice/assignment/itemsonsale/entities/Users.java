/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *
 * @author aida.erfanian
 */
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String username;
    private String recommendations;
    
    @OneToMany(mappedBy="users")
    private Set<Orders> orders;

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.recommendations = password;
    }

    public String getUsername() {
        return username;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
