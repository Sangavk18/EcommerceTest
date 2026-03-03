package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id="first-name")
    WebElement firstName;

    @FindBy(id="last-name")
    WebElement lastName;

    @FindBy(id="postal-code")
    WebElement postalCode;

    @FindBy(id="continue")
    WebElement continueBtn;

    @FindBy(id="finish")
    WebElement finishBtn;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage enterDetails() {

        wait.until(ExpectedConditions.visibilityOf(firstName))
                .sendKeys("John");

        wait.until(ExpectedConditions.visibilityOf(lastName))
                .sendKeys("Doe");

        wait.until(ExpectedConditions.visibilityOf(postalCode))
                .sendKeys("400001");

        wait.until(ExpectedConditions.elementToBeClickable(continueBtn))
                .click();

        return this;
    }

    public ConfirmationPage finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn))
                .click();
        return new ConfirmationPage(driver);
    }
}