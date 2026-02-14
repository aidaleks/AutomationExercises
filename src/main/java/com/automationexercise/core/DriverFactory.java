package com.automationexercise.core;

import com.automationexercise.utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class DriverFactory {
    public static WebDriver createDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return createChromeDriver();

            case FIREFOX:
                return new FirefoxDriver();

            case EDGE:
                return new EdgeDriver();

            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-notifications",
                "--disable-infobars"
        );

        if (Config.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        ChromeDriver driver = new ChromeDriver(options);
        driver.executeCdpCommand(
                "Network.setBlockedURLs",
                Map.of("urls", List.of("*ads*", "*doubleclick*", "*analytics*"))
        );

        driver.executeCdpCommand("Network.enable", Map.of());

        return driver;
    }
}
