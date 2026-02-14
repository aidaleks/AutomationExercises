package com.automationexercise.pages;

import org.openqa.selenium.By;

public class DeleteAccountPage extends BasePage {

    private final By accountDeletedHeader = By.cssSelector("[data-qa='account-deleted']");
    private final By continueBtn = By.cssSelector("[data-qa='continue-button']");

    public boolean isLoaded() {
        return isVisible(accountDeletedHeader);
    }

    public HomePage clickContinue() {
        click(continueBtn);
        return new HomePage();
    }
}
