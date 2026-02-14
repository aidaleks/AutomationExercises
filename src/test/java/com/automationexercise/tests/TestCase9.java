package com.automationexercise.tests;

import com.automationexercise.base.BaseTest;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.utils.Config;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestCase9 extends BaseTest {

    String search = Config.getSearchProduct();

    @Test(description = "TestCase9")
    @Description("Verify search returns products matching entered keyword")
    public void shouldDisplayMatchingProductsAfterSearch() {
        verifyHomePageLoaded();
        ProductsPage productsPage = openProductsPage();
        searchProduct(productsPage, search);
        verifySearchResults(productsPage, search);
    }

    @Step("Open Products page and verify it is loaded")
    private ProductsPage openProductsPage() {
        ProductsPage page = homePage.openProductsPage();
        assertTrue(page.isLoaded(), "All Products page is not displayed");
        return page;
    }

    @Step("Search product: {product}")
    private void searchProduct(ProductsPage page, String product) {
        page.fillSearchProductInput(product);
        assertTrue(page.isSearchResultsVisible(),
                "'Searched Products' header not displayed");
    }

    @Step("Verify search results match: {product}")
    private void verifySearchResults(ProductsPage page, String product) {

        List<String> results = page.getSearchResultNames();

        assertFalse("No search results found", results.isEmpty());
        for (String name : results) {
            assertTrue(
                    name.toLowerCase().contains(product.toLowerCase()),
                    "Product does not match search: " + name
            );
        }
    }
}
