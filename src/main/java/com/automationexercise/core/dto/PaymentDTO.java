package com.automationexercise.core.dto;

public class PaymentDTO {
    private String nameOnCard;
    private String cardNumber;
    private String cardCvc;
    private String cardExpiryMonth;
    private String cardExpiryYear;

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardCvc() {
        return cardCvc;
    }

    public String getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public String getCardExpiryYear() {
        return cardExpiryYear;
    }
}
