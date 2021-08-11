package com.agent.product.controller;

import com.agent.product.model.dto.LoginRequestDTO;
import com.agent.product.model.dto.LoginResponseDTO;
import com.agent.product.model.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@Rollback(true)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRest;
    HttpHeaders headers;
    String token;

    @Value("${gatewayAddress}")
    private String gatewayAddress;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        LoginRequestDTO dto = new LoginRequestDTO("admin", "admin");
        HttpEntity<LoginRequestDTO> request = new HttpEntity<LoginRequestDTO>(dto, headers);
        ResponseEntity<LoginResponseDTO> response = testRest.postForEntity(gatewayAddress + "/agent-shop-service" + "/auth/login", request, LoginResponseDTO.class);
        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        token = "Bearer " + response.getBody().getToken();
        headers.add("Content-Type", "application/json");
    }

    @Test
    public void addProductSuccess() {
        ProductDTO productDTO = new ProductDTO(null, "Proizvod", 11.0, 11l, "");
        HttpEntity<ProductDTO> request = new HttpEntity<ProductDTO>(productDTO, headers);

        ResponseEntity<ProductDTO> response = testRest.postForEntity("/api/product",
                request,
                ProductDTO.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proizvod", response.getBody().getName());
    }

    @Test
    public void edit_productSuccess() {
        ProductDTO productDTO = new ProductDTO(100l, "Proizvod2", 11.0, 11l, "");
        HttpEntity<ProductDTO> request = new HttpEntity<ProductDTO>(productDTO, headers);

        ResponseEntity<ProductDTO> response = testRest.exchange("/api/product",
                HttpMethod.PUT,
                request,
                ProductDTO.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Proizvod2", response.getBody().getName());
    }

    @Test
    public void deleteProduct() {
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Boolean> response = testRest.exchange("/api/product/101",
                HttpMethod.DELETE,
                request,
                Boolean.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }
}
