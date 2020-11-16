/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import io.microservice.assignment.itemsonsale.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author aida.erfanian
 */
@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {

}
