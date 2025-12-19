package base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScenarioContext;
import utils.WaitUtil;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WaitUtil waitUtil;
    protected ScenarioContext scenarioContext;
    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        this.waitUtil = new WaitUtil(driver);
        this.scenarioContext = new ScenarioContext();
    }

    public void sendKeys(By locator, String value) {
        LOG.info("sendKeys: " + locator + " with " + value);
        WebElement element = waitUtil.waitForVisibilityOfElementLocated(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void clickbutton(By locator) {
        WebElement element = waitUtil.waitForPresenceOfElementLocated(locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    public void click(By locator) {
        LOG.info("click: " + locator);
        WebElement element = waitUtil.waitForElementToBeClickable(locator);
        element.click();
    }

    public String getText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitUtil.waitForVisibilityOfElementLocated(locator);
        return element.getText();
    }

    public String getSelectedOptionText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitUtil.waitForVisibilityOfElementLocated(locator);
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
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element
        );
        actions.moveToElement(element)
                .perform();
    }

    public void browserBack() {
        driver.navigate().back();
        waitUtil.waitForPageLoaded();
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

}