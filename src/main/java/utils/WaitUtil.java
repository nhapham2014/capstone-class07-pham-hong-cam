package utils;

import config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtil {
    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
   protected WebDriverWait wait;
    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getInt("timeout")),
                Duration.ofMillis(ConfigReader.getInt("polling"))
        );
    }
    public WebElement waitForVisibilityOfElementLocated(By locator) {
        LOG.info("waitForVisibilityOfElementLocated: " + locator);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        LOG.info("waitForElementToBeClickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForPresenceOfElementLocated(By locator) {
        LOG.info("waitForPresenceOfElementLocated: " + locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitForPageLoaded() {

        wait.until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return js.executeScript("return document.readyState").toString().equals("complete");
        });
    }
}
