package com.automationexercise.pages;

import org.openqa.selenium.By;
import com.automationexercise.core.dto.PaymentDTO;
import com.automationexercise.utils.JavaScriptUtility;

public class PaymentPage extends BasePage {

    private final By paymentHeader = By.xpath("//li[normalize-space()='Payment']");
    private final By nameOnCardField = By.name("name_on_card");
    private final By cardNumberField = By.name("card_number");
    private final By cardCvcField = By.name("cvc");
    private final By cardExpiryField = By.name("expiry_month");
    private final By yearExpiryField = By.name("expiry_year");
    private final By payButton = By.id("submit");
    private final By successMessage =
            By.xpath("//p[normalize-space()='Congratulations! Your order has been confirmed!']");

    public boolean isLoaded() {
        return isVisible(paymentHeader);
    }

    public PaymentPage enterPaymentDetails(PaymentDTO data) {
        type(nameOnCardField, data.getNameOnCard());
        type(cardNumberField, data.getCardNumber());
        type(cardCvcField, data.getCardCvc());
        type(cardExpiryField, data.getCardExpiryMonth());
        type(yearExpiryField, data.getCardExpiryYear());
        JavaScriptUtility.scrollToElementJS(payButton);
        click(payButton);
        return this;
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
