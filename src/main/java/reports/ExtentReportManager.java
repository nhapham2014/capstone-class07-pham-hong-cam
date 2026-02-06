package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // mỗi thread 1 ExtentTest
    private static final String REPORT_PATH = "test-output/ExtentReport_" +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
            ".html";
    private static final String SCREENSHOT_PATH = "test-output/screenshots/";

    public static void initializeExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    private static ExtentTest getTest() {
        return test.get();
    }

    public static void info(String msg) {
        getTest().info(msg);
    }

    public static void pass(String msg) {
        getTest().pass(msg);
    }

    public static void fail(String msg) {
        getTest().fail(msg);
    }

    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";

            File destFile = new File(SCREENSHOT_PATH + fileName);
            destFile.getParentFile().mkdirs(); // ⭐ FIX QUAN TRỌNG

            FileUtils.copyFile(sourceFile, destFile);

            // ⚠️ ĐƯỜNG DẪN PHẢI RELATIVE so với file HTML
            String relativePath = "screenshots/" + fileName;

            getTest().fail(
                    "Screenshot captured",
                    MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build()
            );

        } catch (Exception e) {
            getTest().warning("Could not capture screenshot: " + e.getMessage());
        }
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
    public static void verifyTrue(
            boolean actual,
            String message,
            WebDriver driver
    ) {
        try {
            org.testng.Assert.assertTrue(actual);
            getTest().pass("✅ " + message);
        } catch (AssertionError e) {
            getTest().fail("❌ " + message);
            getTest().fail("Expected: true");
            getTest().fail("Actual: " + actual);

           // captureScreenshot(driver, message.replace(" ", "_"));
            throw e;
        }
    }
    public static void verifyEqualsString(
            String actual,
            String expected,
            String message,
            WebDriver driver
    ) {
        getTest().info("Expected: <b>" + expected + "</b>");
        getTest().info("Actual: <b>" + actual + "</b>");

        if (!actual.equals(expected)) {
            getTest().fail("❌ " + message);
         //   captureScreenshot(driver, message.replace(" ", "_"));
        } else {
            getTest().pass("✅ Verify passed");
        }
    }
    public static void verifyEqualsNumber(
            int actual,
            int expected,
            String message,
            WebDriver driver
    ) {
        getTest().info("Expected: <b>" + expected + "</b>");
        getTest().info("Actual: <b>" + actual + "</b>");

        if (actual!=expected) {
            getTest().fail("❌ " + message);
          //  captureScreenshot(driver, message.replace(" ", "_"));
        } else {
            getTest().pass("✅ Verify passed");
        }
    }


    public static void verifyEqualsStringList(List<String> actual, List<String> expected, String message, WebDriver driver) {
        getTest().info("Expected: <b>" + expected + "</b>");
        getTest().info("Actual: <b>" + actual + "</b>");

        if (actual!=expected) {
            getTest().fail("❌ " + message);
            //  captureScreenshot(driver, message.replace(" ", "_"));
        } else {
            getTest().pass("✅ Verify passed");
        }
    }
}
