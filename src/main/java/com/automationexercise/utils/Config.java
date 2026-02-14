package com.automationexercise.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                properties.load(in);
            }
            // System properties override file values
            System.getProperties().forEach((k, v) -> properties.setProperty(String.valueOf(k), String.valueOf(v)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private Config() {
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "http://localhost:8080");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static boolean isWindowMaximize() {
        return Boolean.parseBoolean(properties.getProperty("windowMaximize", "true"));
    }

    public static Duration getImplicitWait() {
        return Duration.ofSeconds(Long.parseLong(properties.getProperty("implicitWaitSec", "5")));
    }

    public static Duration getPageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(properties.getProperty("pageLoadTimeoutSec", "60")));
    }

    public static Duration getScriptTimeout() {
        return Duration.ofSeconds(Long.parseLong(properties.getProperty("scriptTimeoutSec", "30")));
    }

    public static String getSearchProduct() {
        return properties.getProperty("search.product.input");
    }

    public static String getSignupName() {
        return properties.getProperty("signupName");
    }

    public static String getSignupEmail() {
        return properties.getProperty("signupEmail");
    }

    public static String getLoginEmail() {
        return properties.getProperty("loginEmail");
    }

    public static String getLoginPassword() {
        return properties.getProperty("loginPassword");
    }

    public static String getLoginUserName() {
        return properties.getProperty("loginUserName");
    }
}
