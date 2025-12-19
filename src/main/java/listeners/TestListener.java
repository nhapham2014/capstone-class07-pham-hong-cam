package listeners;
import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    public static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("===== START TEST: " + methodName + " =====");
        ExtentReportManager.createTest(
                result.getTestClass().getName() + " - " + methodName
        );

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("===== PASSED TEST: " + methodName + " =====");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("===== FAILED TEST: " + methodName + " =====", result.getThrowable());
        //take screen lúc failed
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        takeScreenshot(driver, result.getName());

    }
    private void takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + testName + ".png");

        try {
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.warn("===== SKIPPED TEST: " + methodName + " =====");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info(">>> START TEST SUITE: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info(">>> FINISH TEST SUITE: " + context.getName());
    }


}