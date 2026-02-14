package com.automationexercise.tests;

import static org.testng.Assert.assertTrue;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import com.automationexercise.base.BaseTest;
import io.qameta.allure.Step;

public class TestCase25 extends BaseTest {

    @Test(description = "TestCase25")
    @Description("Verify Scroll Down and Scroll Up using 'Arrow' button functionality")
    public void verifyScrollDownAndScrollUpUsingArrowButtonFunctionality() {
        verifyHomePageLoaded();
        verifySubscriptionVisibleAfterScrollDown();
        verifyPageScrolledUpSuccessfully();
    }

    @Step("Scroll down page to bottom and verify 'SUBSCRIPTION' is visible")
    private void verifySubscriptionVisibleAfterScrollDown() {
        assertTrue(
                homePage.isSubscriptionVisible(),
                "'Subscription' section is not visible after scrolling down"
        );
    }

    @Step("Click scroll up arrow and verify top header text is visible")
    private void verifyPageScrolledUpSuccessfully() {
        assertTrue(
                homePage.isAutEngineerHeaderVisible(),
                "Page did not scroll up or header text not visible"
        );
    }
}
