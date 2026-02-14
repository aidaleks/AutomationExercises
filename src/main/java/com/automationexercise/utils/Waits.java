package com.automationexercise.utils;

import com.automationexercise.core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private Waits() {
    }

    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void fluentWaitUntilVisible(int seconds, By locator) {
        FluentWait fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), getExplicitWaitTimeout());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static Duration getExplicitWaitTimeout() {
        try {
            // Prefer a longer explicit timeout than implicit wait
            Duration pageLoad = Config.getPageLoadTimeout();
            Duration fallback = Duration.ofSeconds(15);
            return pageLoad != null && pageLoad.getSeconds() >= 10 ? Duration.ofSeconds(15) : fallback;
        } catch (Exception e) {
            return Duration.ofSeconds(15);
        }
    }
}
