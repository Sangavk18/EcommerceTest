package tests;

import base.BaseTest;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.ConfirmationPage;
import utils.*;
import org.apache.logging.log4j.*;

public class E2ETest extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(E2ETest.class);

    @DataProvider(name="loginData")
    public Object[][] getLoginData(){
        logger.info("Fetching data from JSON");
        return JsonUtils.getJsonData();
    }

@Test(dataProvider="loginData",
      retryAnalyzer = utils.RetryAnalyzer.class)
public void completeOrderTest(String username,
                              String password){

    logger.info("Starting Test for user: " + username);

    LoginPage login = new LoginPage(driver);

    // Perform login
    var loginResult = login.enterUsername(username)
                            .enterPassword(password)
                            .clickLogin();

    // Handle locked user case
    if(username.equalsIgnoreCase("locked_out_user")){
        logger.info("Validating locked user error message");
        Assert.assertTrue(login.isErrorVisible(),
                "Error message not displayed for locked user");
        return;
    }

    // Continue normal flow for valid user
    ConfirmationPage confirmation =
            loginResult.addProductToCart()
                       .goToCart()
                       .clickCheckout()
                       .enterDetails()
                       .finishOrder();

    Assert.assertEquals(
            confirmation.getConfirmationMessage(),
            "Thank you for your order!");

    logger.info("Test Passed Successfully");
}
}

