package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id="checkout")
    WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn))
                .click();
        return new CheckoutPage(driver);
    }
}