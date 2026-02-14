package com.automationexercise.pages;

import com.automationexercise.utils.Waits;
import org.openqa.selenium.By;

public class LoggedInHomePage extends BasePage {

    private final By deleteAccountBtn = By.cssSelector("a[href='/delete_account']");
    private final By usernameText = By.xpath("//a[contains(.,'Logged in as')]/b");
    private final By viewCartLink = By.cssSelector("#cartModal a[href='/view_cart']");
    private final By modal = By.id("cartModal");

    private By addToCartBtn(String productId) {
        return By.cssSelector("a.add-to-cart[data-product-id='" + productId + "']");
    }

    public DeleteAccountPage openDeleteAccountPage() {
        click(deleteAccountBtn);
        return new DeleteAccountPage();
    }

    public String getLoggedUsername() {
        return Waits.waitForVisible(usernameText).getText();
    }

    public LoggedInHomePage addProductToCart(String productId) {
        click(addToCartBtn(productId));
        return this;
    }

    //Navigate to Cart page
    public CartPage clickCartFromModal() {
        Waits.waitForVisible(modal);
        click(viewCartLink);
        return new CartPage();
    }
}
