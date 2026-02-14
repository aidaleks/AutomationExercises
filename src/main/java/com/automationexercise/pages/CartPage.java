package com.automationexercise.pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By shoppingCartHeader = By.xpath("//li[normalize-space()='Shopping Cart']");
    private final By proceedToCheckoutBtn = By.xpath("//a[normalize-space()='Proceed To Checkout']");

    public boolean isLoaded() {
        return isVisible(shoppingCartHeader);
    }

    //Navigate to Checkout page
    public CheckoutPage clickProceedToCheckout() {
        click(proceedToCheckoutBtn);
        return new CheckoutPage();
    }
}
