package com.automationexercise.pages;

import org.openqa.selenium.By;

import java.util.List;

public class CheckoutPage extends BasePage {

    private final By checkoutHeader = By.xpath("//li[normalize-space()='Checkout']");
    private final By addressDelivery = By.xpath("//ul[contains(@id,'address_delivery')]//li");
    private final By totalPrice =
            By.xpath("//td/p[@class='cart_total_price']");
    private final By commentTextArea = By.xpath("//*[@id='ordermsg']/textarea");
    private final By placeOrderBTN = By.cssSelector("a[href='/payment']");

    public boolean isLoaded() {
        return isVisible(checkoutHeader);
    }

    public List<String> getDeliveryAddressLines() {
        return driver.findElements(addressDelivery)
                .stream()
                .map(e -> e.getText().trim())
                .filter(s -> !s.isEmpty())
                .toList();
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public CheckoutPage enterComment(String text) {
        type(commentTextArea, text);
        return this;
    }

    //Navigate to Payment page
    public PaymentPage clickPlaceOrder() {
        click(placeOrderBTN);
        return new PaymentPage();
    }
}
