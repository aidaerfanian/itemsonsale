/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import io.microservice.assignment.itemsonsale.entities.Items;
import io.microservice.assignment.itemsonsale.entities.Orders;
import java.util.LinkedHashSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author aida.erfanian
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT i FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId ORDER BY o.userRanking DESC")
    LinkedHashSet<Items> sortCategoriesByOneUserRanking(@Param("userId") Integer userId);
    
    @Query("SELECT i FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId AND o.favorite = 1 ORDER BY o.userRanking DESC")
    LinkedHashSet<Items> getFavoriteItems(@Param("userId") Integer userId);

    @Query("SELECT it FROM Items it WHERE it.category IN (SELECT i.category FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId AND o.favorite = 1)")
    LinkedHashSet<Items> getItemsOfFavoriteCategories(@Param("userId") Integer userId);

    @Cacheable("onSaleItems")
    @Query("SELECT i FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE o.userRanking = 5 AND i.onSale = 1")
    LinkedHashSet<Items> getHighestRankedOnSaleItems();
    
}
