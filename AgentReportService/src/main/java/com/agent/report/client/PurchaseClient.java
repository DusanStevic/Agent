package com.agent.report.client;

import com.agent.report.model.entity.Product;
import com.agent.report.model.entity.Purchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// app.shop.url from application.properties
@FeignClient(name = "shop", url = "${app.shop.url}")
public interface PurchaseClient {

    @GetMapping(value = "shop/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Purchase> getPurchases();
}
