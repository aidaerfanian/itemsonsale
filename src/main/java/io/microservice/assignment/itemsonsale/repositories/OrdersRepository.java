/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import io.microservice.assignment.itemsonsale.entities.Items;
import io.microservice.assignment.itemsonsale.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
/**
 *
 * @author aida.erfanian
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT i.category FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId ORDER BY o.userRanking DESC")
    List<String> sortCategoriesByOneUserRanking(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT(i.name) FROM Items i WHERE i.category IN (SELECT i.category FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId)")
    List<String> getDistictCategoriesByOneUserRanking(@Param("userId") Integer userId);    

    @Query("SELECT i.name FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE u.userId = :userId ORDER BY o.userRanking DESC")
    List<String> getItemsByOneUserRanking(@Param("userId") Integer userId); 
    
    @Cacheable("onSaleItems")
    @Query("SELECT i FROM Orders o INNER JOIN o.users u INNER JOIN o.items i WHERE o.userRanking = 5 AND i.onSale = 1")
    List<Items> getHighestRankedOnSaleItems();
    
}
