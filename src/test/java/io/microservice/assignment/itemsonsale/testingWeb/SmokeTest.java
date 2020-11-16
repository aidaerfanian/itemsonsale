/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.testingWeb;

import io.microservice.assignment.itemsonsale.RequestsController;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author aida.erfanian
 */
public class SmokeTest {
	@Autowired
	private RequestsController requestsController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(requestsController).isNotNull();
	}
}
