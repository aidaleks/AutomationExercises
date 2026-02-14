package com.automationexercise.base;

import com.automationexercise.core.Browser;
import com.automationexercise.core.DriverFactory;
import com.automationexercise.core.DriverManager;
import com.automationexercise.pages.HomePage;
import com.automationexercise.utils.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;
    protected HomePage homePage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("CHROME") String browserName) {

        // Resolve browser enum
        Browser browser = Browser.valueOf(browserName.toUpperCase());

        // Create driver via centralized factory
        driver = DriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);

        // Browser window settings
        if (Config.isWindowMaximize()) {
            driver.manage().window().maximize();
        }

        // Timeouts
        driver.manage().timeouts().pageLoadTimeout(Config.getPageLoadTimeout());
        driver.manage().timeouts().implicitlyWait(Config.getImplicitWait());
        driver.manage().timeouts().scriptTimeout(Config.getScriptTimeout());

        // Core initialization
        DriverManager.setDriver(driver);

        // Base URL logic
        baseUrl = Config.getBaseUrl();
        openBaseUrl();

        // Page initialization
        homePage = new HomePage();
    }

    protected void openBaseUrl() {
        driver.get(baseUrl);
    }

    @Step("Verify that home page is visible successfully")
    protected void verifyHomePageLoaded() {
        assertTrue(homePage.isLoaded(), "Home page should be visible");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
