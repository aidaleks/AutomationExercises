package com.automationexercise.pages;

import org.openqa.selenium.By;

import static com.automationexercise.utils.JavaScriptUtility.*;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector(".logo img");
    private final By slider = By.id("slider-carousel");
    private final By signupLoginBtn = By.cssSelector("a[href='/login']");
    private final By productsBtn = By.cssSelector("a[href='/products']");
    private final By subscriptionHeader = By.xpath("//h2[normalize-space()='Subscription']");
    private final By scrollUpBtn = By.id("scrollUp");
    private final By autEngineerHeader = By.xpath("//h2[contains(text(),'Automation Engineers')]");

    public boolean isLoaded() {
        return isVisible(logo) && isVisible(slider);
    }

    //Navigate to Login page
    public LoginPage openLoginPage() {
        click(signupLoginBtn);
        return new LoginPage();
    }

    //Navigate to Products page
    public ProductsPage openProductsPage() {
        click(productsBtn);
        return new ProductsPage();
    }

    public boolean isSubscriptionVisible() {
        scrollToElementJS(subscriptionHeader);
        return isVisible(subscriptionHeader);
    }

    public boolean isAutEngineerHeaderVisible() {
        click(scrollUpBtn);
        return isVisible(autEngineerHeader);
    }
}
