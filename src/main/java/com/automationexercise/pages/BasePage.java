package com.automationexercise.pages;

import com.automationexercise.core.DriverManager;
import com.automationexercise.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected WebDriver driver;

    // Constructor fetches the driver from DriverManager for current thread
    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected WebElement find(By locator) {
        return Waits.waitForPresence(locator);
    }

    protected WebElement visible(By locator) {
        return Waits.waitForVisible(locator);
    }

    protected WebElement clickable(By locator) {
        return Waits.waitForClickable(locator);
    }

    protected void click(By locator) {
        clickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return visible(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            Waits.waitForVisible(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void selectRadio(By locator) {
        WebElement radio = Waits.waitForClickable(locator);
        if (!radio.isSelected()) {
            radio.click();
        }
    }

    protected void check(By locator) {
        WebElement el = Waits.waitForClickable(locator);
        if (!el.isSelected()) {
            el.click();
        }
    }

    protected void selectByText(By locator, String text) {
        WebElement dropdown = Waits.waitForVisible(locator);
        new Select(dropdown).selectByVisibleText(text);
    }
}
