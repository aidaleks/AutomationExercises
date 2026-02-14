package com.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By allProductsHeader =
            By.xpath("//h2[normalize-space()='All Products']");
    private final By searchProductsHeader =
            By.xpath("//h2[normalize-space()='Searched Products']");
    private final By searchProductField = By.id("search_product");
    private final By searchBtn = By.id("submit_search");
    private final By searchResultsNames =
            By.xpath("//div[contains(@class,'productinfo text-center')]//p");

    public boolean isLoaded() {
        return isVisible(allProductsHeader);
    }

    public ProductsPage enterSearchCriteria(String text) {
        type(searchProductField, text);
        return this;
    }

    public ProductsPage clickSearch() {
        click(searchBtn);
        return this;
    }

    public boolean isSearchResultsVisible() {
        return isVisible(searchProductsHeader);
    }

    public ProductsPage fillSearchProductInput(String searchProduct) {
        enterSearchCriteria(searchProduct);
        clickSearch();
        return this;
    }

    public List<String> getSearchResultNames() {
        return driver.findElements(searchResultsNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
