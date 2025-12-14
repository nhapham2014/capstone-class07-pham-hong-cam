package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class BasePage {

    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitspecial;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        waitspecial = new WebDriverWait(driver, Duration.ofSeconds(600));

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

    public void sendKeys(By locator, String value) {
        LOG.info("sendKeys: " + locator + " with " + value);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        element.sendKeys(value);
    }
    public void clickbutton(By locator) {
        WebElement element = waitForPresenceOfElementLocated(locator);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }
    public LocalDateTime changeToDateTime(String dateStr) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        return dateTime;
    }

    public void click(By locator) {
        LOG.info("click: " + locator);
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public String getText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return element.getText();
    }

    public String getSelectedOptionText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return new Select(element).getFirstSelectedOption().getText();
    }

    public void waitOptionsDropDownLoaded(By locator) {
        LOG.info("waitOptionsDropDownLoaded: " + locator);
        wait.until(driver -> {
            Select s = new Select(driver.findElement(locator));
            return s.getOptions().size() > 1; // có option mới => loaded
        });

    }
    public void waitValueDefaultOnly(By locator) {
        wait.until(driver ->
                driver.findElements(locator).size() == 1
        );
    }
    public void waitForPageLoaded() {

        wait.until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return js.executeScript("return document.readyState").toString().equals("complete");
        });
    }
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public int getOptionsCount(By locator) {
        List<WebElement> options = driver.findElements(locator);
        return options.size();
    }


    public void selectOptionByText(By dropdownLocator, String text) {
        Select select = new Select(driver.findElement(dropdownLocator));
        select.selectByVisibleText(text);
    }
    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }
    public void browserBack() {
        driver.navigate().back();
        waitForPageLoaded();
    }
    public void reloadPage() {
        driver.navigate().refresh();
    }

}