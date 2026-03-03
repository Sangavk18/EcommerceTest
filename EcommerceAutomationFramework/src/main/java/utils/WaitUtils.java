package utils;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(10)
        );
    }

    public static WebElement waitForElementVisible(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForElementClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }
}