package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String path = "screenshots/" + testName + ".png";

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(path);
            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}