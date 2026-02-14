package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.core.JsonDataReader;
import com.automationexercise.core.dto.AccountInfoDTO;
import com.automationexercise.core.dto.PaymentDTO;
import com.automationexercise.pages.*;
import com.automationexercise.utils.Config;
import com.automationexercise.utils.Util;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestCase16 extends BaseTest {

    String email = "email" + Util.generateCurrentDateAndTime() + "@test.com";
    String loginPassword = Config.getLoginPassword();
    String loginUserName = Config.getLoginUserName();
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private LoggedInHomePage loggedPage;
    private DeleteAccountPage page;

    private AccountInfoDTO accountInfoDTO =
            JsonDataReader.loadResource("accountData.json", AccountInfoDTO.class);

    @Test(description = "Preparation test for creating user")
    public void createUser() {
        verifyHomePageLoaded();
        LoginPage loginPage = openLoginPage();
        AccountCreatedPage accountPage = signupUser(loginPage);
        fillFormAndCreateAccount(accountPage);
        verifyAccountCreated(accountPage);
    }

    @Test(dependsOnMethods = "createUser",
            description = "TestCase16")
    @Description("Verify that a logged-in user can add products to cart, place an order successfully, and delete the account")
    public void shouldPlaceOrderSuccessfullyAndDeleteAccountAfterLogin() {
        verifyHomePageLoaded();
        LoginPage loginPage = openLoginPage();
        loggedPage = verifyUserLoggedIn(loginPage);
        addProductsToCart();
        openCartAndVerify();
        proceedToCheckout();
        verifyCheckoutPage();
        verifyDeliveryAddress(checkoutPage, accountInfoDTO);
        verifyTotalPriceIsNotEmpty();
        enterOrderComment();
        placeOrder();
        enterPaymentDetails();
        verifyOrderSuccess();
        page = openDeleteAccountPage(loggedPage);
        verifyUserDeleted(page);
    }

    @Step("Open Login page and verify it is displayed")
    private LoginPage openLoginPage() {
        LoginPage loginPage = homePage.openLoginPage();
        assertTrue(loginPage.isLoaded(), "Login page should be displayed");
        return loginPage;
    }

    @Step("Login with valid credentials")
    private LoggedInHomePage verifyUserLoggedIn(LoginPage loginPage) {
        LoggedInHomePage page =
                loginPage.login(email, loginPassword);
        assertEquals(
                page.getLoggedUsername(),
                loginUserName,
                "Logged username mismatch"
        );
        return page;
    }

    @Step("Add products to cart")
    private void addProductsToCart() {
        loggedPage
                .addProductToCart("1")
                .addProductToCart("2");
    }

    @Step("Open cart and verify cart page")
    private void openCartAndVerify() {
        cartPage = loggedPage.clickCartFromModal();
        assertTrue(cartPage.isLoaded(), "Cart page not loaded");
    }

    @Step("Click Proceed To Checkout")
    private void proceedToCheckout() {
        checkoutPage = cartPage.clickProceedToCheckout();
    }

    @Step("Verify checkout page loaded")
    private void verifyCheckoutPage() {
        assertTrue(checkoutPage.isLoaded(), "Checkout page not loaded");
    }

    @Step("Verify delivery address matches entered account data")
    private void verifyDeliveryAddress(CheckoutPage page, AccountInfoDTO dto) {

        List<String> actual = page.getDeliveryAddressLines();

        List<String> expected = List.of(
                "Mrs. " + dto.getFirstName() + " " + dto.getLastName(),
                dto.getCompany(),
                dto.getAddress1(),
                dto.getAddress2(),
                dto.getCity() + " " + dto.getState() + " " + dto.getZipcode(),
                dto.getCountry(),
                dto.getMobileNumber()
        );

        assertTrue(actual.containsAll(expected),
                "Delivery address does not match expected data.\nActual: " + actual);
    }

    @Step("Verify total price")
    private void verifyTotalPriceIsNotEmpty() {
        assertFalse(checkoutPage.getTotalPrice().isEmpty(),
                "Total price on checkout page should not be empty");
    }

    @Step("Enter order comment")
    private void enterOrderComment() {
        checkoutPage.enterComment("Test order");
    }

    @Step("Click place order")
    private void placeOrder() {
        paymentPage = checkoutPage.clickPlaceOrder();
    }

    @Step("Enter payment details")
    private void enterPaymentDetails() {
        PaymentDTO payment = JsonDataReader.loadResource("paymentData.json", PaymentDTO.class);
        paymentPage.enterPaymentDetails(payment);
    }

    @Step("Verify order success message")
    private void verifyOrderSuccess() {
        assertEquals(
                paymentPage.getSuccessMessage(),
                "Congratulations! Your order has been confirmed!"
        );
    }

    @Step("Open delete account page")
    private DeleteAccountPage openDeleteAccountPage(LoggedInHomePage loggedPage) {
        DeleteAccountPage deletePage = loggedPage.openDeleteAccountPage();
        assertTrue(deletePage.isLoaded(),
                "Delete account page not visible");
        return deletePage;
    }

    @Step("Confirm account deletion and verify home page is visible")
    private void verifyUserDeleted(DeleteAccountPage page) {
        page.clickContinue();
        assertTrue(homePage.isLoaded(),
                "Home page should be visible after deletion");
    }

    @Step("Verify that 'ENTER ACCOUNT INFORMATION' is visible")
    private AccountCreatedPage signupUser(LoginPage loginPage) {
        AccountCreatedPage page =
                loginPage.signup(loginUserName, email);
        assertTrue(page.isLoaded(),
                "Account Information page should be displayed");
        return page;
    }

    @Step("Verify that 'ACCOUNT CREATED!' is visible")
    private void fillFormAndCreateAccount(AccountCreatedPage page) {
        page.fillAccountForm(accountInfoDTO)
                .clickCreateAccount();
    }

    @Step("Verify that 'Logged in as username' is visible")
    private LoggedInHomePage verifyAccountCreated(AccountCreatedPage page) {
        assertTrue(
                page.isAccountCreatedVisible(),
                "Account creation confirmation message is not displayed"
        );
        LoggedInHomePage loggedInHome = page.clickContinue();
        assertEquals(
                loggedInHome.getLoggedUsername(),
                loginUserName,
                "Logged username mismatch"
        );
        return loggedInHome;
    }
}
