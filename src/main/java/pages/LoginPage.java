package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtil;

public class LoginPage extends CommonPage {
    HomePage homePage;


    private By byTxtAccountLogin = By.id("taiKhoan");;
    private By byTxtPasswordLogin = By.id("matKhau");;
    private By byBtnLogin = By.xpath("//button[.='Đăng nhập']");;
    private By byLblLoginMsg = By.id("swal2-title");;
    private By byBtnClose = By.xpath("//button[contains(text(),'Đóng')]");
    private By byTxtErrorUsername = By.xpath("//*[@id='taiKhoan-helper-text']");
    private By byTxtErrorPassword = By.xpath("//*[@id='matKhau-helper-text']");
    private By byMsgError = By.xpath("//div[@role='alert']/div[@class='MuiAlert-message']");
    private By byChbxRemember = By.xpath("//input[@name='remember']");
    private By byLnkSignUp = By.xpath("//h3[contains(text(),'Đăng ký')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String account) {
        LOG.info("enterAccount: " + account);
        sendKeys(byTxtAccountLogin, account);
    }

    public void enterPassword(String password) {
        LOG.info("enterPassword: " + password);
        sendKeys(byTxtPasswordLogin, password);
    }

    public void clickLoginButtonWithErrorCase() {
        LOG.info("clickLoginWithErrorCase");
        click(byBtnLogin);
    }
    public HomePage clickLogin(){
        LOG.info("clickLogin");
        click(byBtnLogin);
        waitUtil.waitForPageLoaded();
        homePage = new HomePage(driver);
        return homePage;
    }
    public void clickClose(){
        click(byBtnClose);
    }

    public String getMessage() {
        LOG.info("getMessage");
        return getText(byLblLoginMsg);
    }

    public HomePage login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        return clickLogin();
    }
    public String getErrorRequireUsername(){
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorUsername);
        return getText(byTxtErrorUsername);
    }
    public String getErrorRequirePassword(){
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorPassword);
        return getText(byTxtErrorPassword);
    }
    public String getErrorMessage(){
        waitUtil.waitForVisibilityOfElementLocated(byMsgError);
        return getText(byMsgError);
    }
    public void clickRememberChkbx(){
        WebElement checkbox = driver.findElement(byChbxRemember);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbox);

    }
    public RegisterPage clickSignUpLink(){
        click(byLnkSignUp);
        waitUtil.waitForPageLoaded();
        RegisterPage registerPage = new RegisterPage(driver);
        return registerPage;
    }
    public String getValueAccount(){
        return driver.findElement(byTxtAccountLogin).getAttribute("value");
    }
    public String getValuePassword(){
        return driver.findElement(byTxtPasswordLogin).getAttribute("value");
    }
}
