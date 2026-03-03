package utils;

import com.aventstack.extentreports.*;
import org.testng.*;
import base.BaseTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().pass("Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (test.get() == null) {
            ExtentTest extentTest =
                    extent.createTest(result.getName());
            test.set(extentTest);
        }

        test.get().fail(result.getThrowable());

        String path = ScreenshotUtil.captureScreenshot(
                BaseTest.driver,
                result.getName());

        try {
            test.get().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}