package com.automationexercise.pages;

import com.automationexercise.core.dto.AccountInfoDTO;
import com.automationexercise.utils.JavaScriptUtility;
import com.automationexercise.utils.Waits;
import org.openqa.selenium.By;

public class AccountCreatedPage extends BasePage {
    private final By accountInfoTxt = By.xpath("//h2/b[contains(text(), 'Enter Account Information')]");
    private final By mrsRadio = By.id("id_gender2");
    private final By nameField = By.id("name");
    private final By passwordField = By.id("password");
    private final By dayDropDown = By.id("days");
    private final By monthDropDown = By.id("months");
    private final By yearsDropDown = By.id("years");
    private final By newsLetterCheckBox = By.id("newsletter");
    private final By optinCheckBox = By.id("optin");
    private final By firstNameField = By.id("first_name");
    private final By lastNameField = By.id("last_name");
    private final By companyField = By.id("company");
    private final By address1Field = By.id("address1");
    private final By address2Field = By.id("address2");
    private final By countryDropDown = By.id("country");
    private final By stateField = By.id("state");
    private final By cityField = By.id("city");
    private final By zipcodeField = By.id("zipcode");
    private final By mobileNumberField = By.id("mobile_number");
    private final By createAccountBTN = By.cssSelector("[data-qa='create-account']");
    private final By accountCreatedHeader = By.cssSelector("[data-qa='account-created']");
    private final By continueBtn = By.cssSelector("[data-qa='continue-button']");

    public boolean isLoaded() {
        return isVisible(accountInfoTxt);
    }

    // ACCOUNT INFO
    public AccountCreatedPage selectMrsTitle() {
        JavaScriptUtility.scrollToElementJS(mrsRadio);
        selectRadio(mrsRadio);
        return this;
    }

    public AccountCreatedPage enterName(String name) {
        JavaScriptUtility.scrollToElementJS(nameField);
        type(nameField, name);
        return this;
    }

    public AccountCreatedPage enterPassword(String password) {
        JavaScriptUtility.scrollToElementJS(passwordField);
        type(passwordField, password);
        return this;
    }

    public AccountCreatedPage selectBirthDay(String day) {
        JavaScriptUtility.scrollToElementJS(dayDropDown);
        selectByText(dayDropDown, day);
        return this;
    }

    public AccountCreatedPage selectBirthMonth(String month) {
        JavaScriptUtility.scrollToElementJS(monthDropDown);
        selectByText(monthDropDown, month);
        return this;
    }

    public AccountCreatedPage selectBirthYear(String year) {
        JavaScriptUtility.scrollToElementJS(yearsDropDown);
        selectByText(yearsDropDown, year);
        return this;
    }

    public AccountCreatedPage subscribeNewsletter() {
        JavaScriptUtility.scrollToElementJS(newsLetterCheckBox);
        check(newsLetterCheckBox);
        return this;
    }

    public AccountCreatedPage enableSpecialOffers() {
        JavaScriptUtility.scrollToElementJS(optinCheckBox);
        check(optinCheckBox);
        return this;
    }

    //ADDRESS INFO
    public AccountCreatedPage enterFirstName(String firstName) {
        JavaScriptUtility.scrollToElementJS(firstNameField);
        type(firstNameField, firstName);
        return this;
    }

    public AccountCreatedPage enterLastName(String lastName) {
        JavaScriptUtility.scrollToElementJS(lastNameField);
        type(lastNameField, lastName);
        return this;
    }

    public AccountCreatedPage enterCompany(String company) {
        JavaScriptUtility.scrollToElementJS(companyField);
        type(companyField, company);
        return this;
    }

    public AccountCreatedPage enterAddress1(String address) {
        JavaScriptUtility.scrollToElementJS(address1Field);
        type(address1Field, address);
        return this;
    }

    public AccountCreatedPage enterAddress2(String address) {
        JavaScriptUtility.scrollToElementJS(address2Field);
        type(address2Field, address);
        return this;
    }

    public AccountCreatedPage selectCountry(String country) {
        JavaScriptUtility.scrollToElementJS(countryDropDown);
        selectByText(countryDropDown, country);
        return this;
    }

    public AccountCreatedPage enterState(String state) {
        JavaScriptUtility.scrollToElementJS(stateField);
        type(stateField, state);
        return this;
    }

    public AccountCreatedPage enterCity(String city) {
        JavaScriptUtility.scrollToElementJS(cityField);
        type(cityField, city);
        return this;
    }

    public AccountCreatedPage enterZipCode(String zip) {
        JavaScriptUtility.scrollToElementJS(zipcodeField);
        type(zipcodeField, zip);
        return this;
    }

    public AccountCreatedPage enterMobile(String mobile) {
        JavaScriptUtility.scrollToElementJS(mobileNumberField);
        type(mobileNumberField, mobile);
        return this;
    }

    // ACTION
    public AccountCreatedPage clickCreateAccount() {
        click(createAccountBTN);
        return new AccountCreatedPage();
    }

    public boolean isAccountCreatedVisible() {
        return Waits.waitForVisible(accountCreatedHeader).isDisplayed();
    }

    public LoggedInHomePage clickContinue() {
        click(continueBtn);
        return new LoggedInHomePage();
    }

    public AccountCreatedPage fillAccountForm(AccountInfoDTO dto) {
        return selectMrsTitle()
                .enterPassword(dto.getPassword())
                .selectBirthDay(dto.getDay())
                .selectBirthMonth(dto.getMonth())
                .selectBirthYear(dto.getYear())
                .subscribeNewsletter()
                .enableSpecialOffers()
                .enterFirstName(dto.getFirstName())
                .enterLastName(dto.getLastName())
                .enterCompany(dto.getCompany())
                .enterAddress1(dto.getAddress1())
                .enterAddress2(dto.getAddress2())
                .selectCountry(dto.getCountry())
                .enterState(dto.getState())
                .enterCity(dto.getCity())
                .enterZipCode(dto.getZipcode())
                .enterMobile(dto.getMobileNumber());
    }
}
