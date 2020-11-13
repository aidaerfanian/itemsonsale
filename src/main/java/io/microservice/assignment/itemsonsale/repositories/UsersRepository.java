/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import io.microservice.assignment.itemsonsale.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author aida.erfanian
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findByUsername(String username);
}
