package com.automationexercise.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By signUpHeader = By.xpath("//h2[normalize-space()='New User Signup!']");
    private final By signupNameField = By.cssSelector("[data-qa='signup-name']");
    private final By signupEmailField = By.cssSelector("[data-qa='signup-email']");
    private final By signupBtn = By.cssSelector("[data-qa='signup-button']");
    private final By loginBtn = By.cssSelector("[data-qa='login-button']");
    private final By loginEmailField = By.cssSelector("[data-qa='login-email']");
    private final By loginPasswordField = By.cssSelector("[data-qa='login-password']");

    public boolean isLoaded() {
        return isVisible(signUpHeader);
    }

    public LoginPage enterName(String name) {
        type(signupNameField, name);
        return this;
    }

    public LoginPage enterEmail(String email) {
        type(signupEmailField, email);
        return this;
    }

    public AccountCreatedPage clickSignup() {
        click(signupBtn);
        return new AccountCreatedPage();
    }

    public AccountCreatedPage signup(String name, String email) {
        return enterName(name)
                .enterEmail(email)
                .clickSignup();
    }

    public LoginPage enterLoginEmail(String email) {
        type(loginEmailField, email);
        return this;
    }

    public LoginPage enterLoginPassword(String password) {
        type(loginPasswordField, password);
        return this;
    }

    public LoggedInHomePage clickLogin() {
        click(loginBtn);
        return new LoggedInHomePage();
    }

    public LoggedInHomePage login(String email, String password) {
        return enterLoginEmail(email)
                .enterLoginPassword(password)
                .clickLogin();
    }
}
