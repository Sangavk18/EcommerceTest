package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.apache.logging.log4j.*;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(LoginPage.class);

    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginBtn;

    // ✅ ADD THIS
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterUsername(String user) {
        logger.info("Entering username: " + user);
        wait.until(ExpectedConditions.visibilityOf(username))
                .clear();
        username.sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        logger.info("Entering password");
        wait.until(ExpectedConditions.visibilityOf(password))
                .clear();
        password.sendKeys(pass);
        return this;
    }

    public ProductPage clickLogin() {
        logger.info("Clicking login button");
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn))
                .click();
        return new ProductPage(driver);
    }

    // ✅ ADD THIS METHOD
    public boolean isErrorVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
