package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.JsonDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class E2ETest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(E2ETest.class);

    // =========================
    // JSON DATA PROVIDER
    // =========================
    @DataProvider(name = "jsonData")
    public Object[][] getData() throws IOException {
        logger.info("Fetching data from JSON");
        return JsonDataReader.getJsonData();
    }

    // =========================
    // E2E TEST
    // =========================
    @Test(dataProvider = "jsonData", retryAnalyzer = utils.RetryAnalyzer.class)
    public void completeOrderTest(String username, String password) {

        logger.info("Starting Test for user: " + username);

        // ---------- LOGIN ----------
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // ---------- NEGATIVE SCENARIO ----------
        if (username.equalsIgnoreCase("locked_out_user")) {

            logger.info("Validating locked user error message");
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Error message not displayed for locked user");

            logger.info("Locked user validation completed");
            return; // stop test here
        }

        // ---------- PRODUCT PAGE ----------
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart();

        logger.info("Product added to cart");

        // ---------- CART ----------
        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        logger.info("Navigated to Checkout");

        // ---------- CHECKOUT ----------
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInformation("Sangram", "Shinde", "400001");
        checkoutPage.finishOrder();

        logger.info("Order finished");

        // ---------- ASSERT ----------
        Assert.assertTrue(checkoutPage.isOrderComplete(),
                "Order completion message not displayed");

        logger.info("Order successfully completed for user: " + username);
    }
}
