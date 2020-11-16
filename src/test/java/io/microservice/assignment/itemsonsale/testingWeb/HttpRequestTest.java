/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.microservice.assignment.itemsonsale.testingWeb;

import static com.google.common.io.Files.map;
import com.google.common.net.HttpHeaders;
import java.net.URL;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.hamcrest.Matchers.contains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author aida.erfanian
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnForbiddenWhenTokenNotProvided() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/welcome",
				String.class)).contains("403");
	}


	@Test
	public void greetingShouldReturnDefaultMessageWhenTokenValid() throws Exception {
            JSONObject body = new JSONObject();
            body.put("username", "default");
            body.put("password", "0000");


            assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/authenticate", body, String.class)).contains("token");
	}
}
