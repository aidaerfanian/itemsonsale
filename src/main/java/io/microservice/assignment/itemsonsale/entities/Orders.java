/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aida.erfanian
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Basic(optional = false)
    @Column(name = "timestamp", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="itemId", referencedColumnName="itemId", nullable=false, insertable=false, updatable=false)
    private Items items;
    
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName="userId", nullable=false, insertable=false, updatable=false)
    private Users users;
    
    private int userRanking;
    private String status;
    private boolean favorite;

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public void setUserRanking(int userRanking) {
        this.userRanking = userRanking;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @JsonIgnoreProperties(value = "refProductPriceEntities", allowSetters = true)
    public Items getItems() {
        return items;
    }

    @JsonIgnoreProperties(value = "refProductPriceEntities", allowSetters = true)
    public Users getUsers() {
        return users;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    public int getUserRanking() {
        return userRanking;
    }

    public String getStatus() {
        return status;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
