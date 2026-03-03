package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ConfirmationPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "complete-header")
    WebElement confirmationMsg;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOf(confirmationMsg))
                .getText();
    }
}