package com.agent.shop.controller;

import com.agent.shop.model.dto.PurchaseRequest;
import com.agent.shop.model.dto.PurchaseResponse;
import com.agent.shop.model.dto.ReportProductResponseDTO;
import com.agent.shop.model.dto.ReportResponseDTO;

import com.agent.shop.model.entity.Purchase;
import com.agent.shop.services.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shop")
public class ShoppingController {

    private final PurchaseService purchaseService;

    public ShoppingController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseResponse> buy(@RequestBody PurchaseRequest purchaseRequest) throws Exception {
        Purchase purchase = purchaseService.buy(purchaseRequest);
        return new ResponseEntity<>(new PurchaseResponse("Success", "200"), HttpStatus.OK);
    }

    @GetMapping(value = "purchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Purchase>> getPurchases() throws Exception {
        List<Purchase> purchase = purchaseService.getPurchases();
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }
}
