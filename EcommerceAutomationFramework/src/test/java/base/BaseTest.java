package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.*;

@Listeners(utils.TestListener.class)
public class BaseTest {

 public static WebDriver driver;
 protected static final Logger logger =
         LogManager.getLogger(BaseTest.class);

 @Parameters("browser")
 @BeforeMethod
 public void setup(String browser) {

     logger.info("Launching browser: " + browser);
     DriverFactory.initDriver(browser);
     driver = DriverFactory.getDriver();
 }

 @AfterMethod
 public void tearDown() {
     logger.info("Closing browser");
     DriverFactory.quitDriver();
 }
}