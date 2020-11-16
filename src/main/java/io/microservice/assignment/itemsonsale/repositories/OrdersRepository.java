/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import io.microservice.assignment.itemsonsale.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author aida.erfanian
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT DISTINCT(o) FROM Orders o INNER JOIN o.users u WHERE u.userId = :userId")
    List<Orders> findByUser(@Param("userId") Integer userId);
    
}
