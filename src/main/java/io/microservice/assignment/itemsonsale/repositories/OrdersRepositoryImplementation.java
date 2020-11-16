/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author aida.erfanian
 */
public abstract class OrdersRepositoryImplementation implements OrdersRepository{
    @CacheEvict(value = "onSaleItems", allEntries = true)
    @Scheduled(cron = "0 0 5 * * *")
    public void evictAllCacheValues() {}
}
