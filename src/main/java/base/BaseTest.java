package base;

import config.ConfigReader;
import drivers.DriverManager;
import drivers.DriverManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;
import utils.ScenarioContext;

import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {

    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ScenarioContext context;
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        LOG.info("Before Suite executed");
        ExtentReportManager.initializeExtentReports();
    }

    //khoi tao driver
    @BeforeClass
    public void beforeClass() {
        LOG.info("Before class executed");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        String browser = System.getProperty("browser", ConfigReader.get("browser"));
        DriverManager driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.createDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        LOG.info("Before Method executed");
        ExtentReportManager.createTest(method.getName());
        context = new ScenarioContext();
        if (needLogin()) {
            doLogin();
        }
    }
    protected boolean needLogin() {
        return false;
    }

    protected void doLogin() {
        homePage.navigateLoginPage();
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
        loginPage.clickClose();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        LOG.info("After Method executed");
        if(result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        LOG.info("After class executed");
    }

    @AfterSuite
    public void afterSuite() {
        ExtentReportManager.flushReports();
        LOG.info("Test Suite completed");
    }
}
