package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//button[contains(text(),'Add to cart')]")
    WebElement addToCart;

    @FindBy(className="shopping_cart_link")
    WebElement cartIcon;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public ProductPage addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart))
                .click();
        return this;
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon))
                .click();
        return new CartPage(driver);
    }
}