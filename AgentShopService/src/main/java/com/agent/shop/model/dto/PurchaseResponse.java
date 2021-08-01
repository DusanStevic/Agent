package com.agent.shop.model.dto;

public class PurchaseResponse {
    String responseMessage;
    String status;

    public PurchaseResponse() {
    }

    public PurchaseResponse(String responseMessage, String status) {
        this.responseMessage = responseMessage;
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
