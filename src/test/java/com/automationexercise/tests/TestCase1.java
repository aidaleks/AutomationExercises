package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.core.JsonDataReader;
import com.automationexercise.core.dto.AccountInfoDTO;
import com.automationexercise.pages.*;
import com.automationexercise.utils.Config;
import com.automationexercise.utils.Util;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestCase1 extends BaseTest {

    String signupName = Config.getSignupName();
    String signupEmail = "signupemail" + Util.generateCurrentDateAndTime() + "@test.com";

    private AccountInfoDTO accountInfoDTO =
            JsonDataReader.loadResource("accountData.json", AccountInfoDTO.class);

    @Test(description = "TestCase1")
    @Description("Verify user can register, login and delete account successfully")
    public void shouldRegisterLoginAndDeleteUser() {
        verifyHomePageLoaded();
        LoginPage loginPage = openLoginPage();
        AccountCreatedPage accountPage = signupUser(loginPage);
        fillFormAndCreateAccount(accountPage);
        LoggedInHomePage loggedInHomePage = verifyAccountCreated(accountPage);
        DeleteAccountPage deletePage = openDeleteAccountPage(loggedInHomePage);
        verifyUserDeleted(deletePage);
    }

    @Step("Verify 'New User Signup!' is visible")
    private LoginPage openLoginPage() {
        LoginPage loginPage = homePage.openLoginPage();
        assertTrue(loginPage.isLoaded(), "Login page should be displayed");
        return loginPage;
    }

    @Step("Verify that 'ENTER ACCOUNT INFORMATION' is visible")
    private AccountCreatedPage signupUser(LoginPage loginPage) {
        AccountCreatedPage page =
                loginPage.signup(signupName, signupEmail);
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
        Assert.assertEquals(
                loggedInHome.getLoggedUsername(),
                signupName,
                "Logged username mismatch"
        );
        return loggedInHome;
    }

    @Step("Open delete account page")
    private DeleteAccountPage openDeleteAccountPage(LoggedInHomePage loggedInHome) {
        DeleteAccountPage deletePage = loggedInHome.openDeleteAccountPage();
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
}
