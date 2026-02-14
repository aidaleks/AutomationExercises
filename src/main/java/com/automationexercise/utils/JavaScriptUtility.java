package com.automationexercise.utils;

import com.automationexercise.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility {
    public static void scrollToElementJS(By locator) {
        WebElement element = Waits.waitForVisible(locator);
        String jsScript = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(jsScript, element);
    }

    public static void clickJS(By locator) {
        WebElement element = Waits.waitForVisible(locator);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }
}
