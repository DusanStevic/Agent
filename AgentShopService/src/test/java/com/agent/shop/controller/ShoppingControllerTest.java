package com.agent.shop.controller;

import static org.junit.Assert.*;

import com.agent.shop.model.dto.LoginRequestDTO;
import com.agent.shop.model.dto.LoginResponseDTO;
import com.agent.shop.model.dto.PurchaseRequest;
import com.agent.shop.model.dto.PurchaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@Rollback(true)
public class ShoppingControllerTest {

    @Autowired
    private TestRestTemplate testRest;
    HttpHeaders headers;
    String token;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        LoginRequestDTO dto = new LoginRequestDTO("admin", "admin");
        HttpEntity<LoginRequestDTO> request = new HttpEntity<LoginRequestDTO>(dto, headers);
        ResponseEntity<LoginResponseDTO> response = testRest.postForEntity("/auth/login", request, LoginResponseDTO.class);
        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        token = "Bearer " + response.getBody().getToken();
        headers.add("Content-Type", "application/json");
    }

    @Test
    public void whenBuy_buy() {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        // This is needed to check if exists in same database where product is run
        purchaseRequest.setProductId(101l);
        purchaseRequest.setQuantity(1);
        purchaseRequest.setName("Pera");
        purchaseRequest.setAddress("Adresa");
        purchaseRequest.setPhone("0600000000");
        HttpEntity<PurchaseRequest> request = new HttpEntity<PurchaseRequest>(purchaseRequest, headers);

        ResponseEntity<PurchaseResponse> response = testRest.postForEntity("/api/shop",
                request,
                PurchaseResponse.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenBuy_NotAvailable() {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        // This is needed to check if exists in same database where product is run
        purchaseRequest.setProductId(2l);
        purchaseRequest.setQuantity(1000);
        purchaseRequest.setName("Pera");
        purchaseRequest.setAddress("Adresa");
        purchaseRequest.setPhone("0600000000");
        HttpEntity<PurchaseRequest> request = new HttpEntity<PurchaseRequest>(purchaseRequest, headers);

        ResponseEntity<PurchaseResponse> response = testRest.postForEntity("/api/shop",
                request,
                PurchaseResponse.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertNotEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getReport() {
        HttpEntity request = new HttpEntity<>(headers);

        ResponseEntity<PurchaseResponse> response = testRest.exchange("/api/shop/report",
                HttpMethod.GET, request,
                PurchaseResponse.class);
        // In order to isolate testing of buying. Product id will be manually checked in database
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
