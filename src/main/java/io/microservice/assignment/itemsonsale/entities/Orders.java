/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private int id;

    @Basic(optional = false)
    @Column(name = "timestamp", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    
    @OneToMany(mappedBy="orders")
    private Set<Items> items;
    
    @ManyToOne
    @JoinColumn(name="id", referencedColumnName="id", nullable=false, insertable=false, updatable=false)
    private Users users;
    
    private int userRanking;
    private String status;
    private boolean favorite;

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
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

    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Set<Items> getItems() {
        return items;
    }

    public Users getUser() {
        return users;
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
