package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void clickLogin() {
        LOG.info("clickLogin");
        click(byBtnLogin);
    }
    public void clickClose(){
        click(byBtnClose);
    }

    public String getMessage() {
        LOG.info("getMessage");
        return getText(byLblLoginMsg);
    }

    public void login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        clickLogin();
    }
    public String getErrorRequireUsername(){
        waitForVisibilityOfElementLocated(byTxtErrorUsername);
        return getText(byTxtErrorUsername);
    }
    public String getErrorRequirePassword(){
        waitForVisibilityOfElementLocated(byTxtErrorPassword);
        return getText(byTxtErrorPassword);
    }
    public String getErrorMessage(){
        waitForVisibilityOfElementLocated(byMsgError);
        return getText(byMsgError);
    }
    public void clickRememberChkbx(){
        WebElement checkbox = driver.findElement(byChbxRemember);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbox);

    }
    public String getValueAccount(){
        return driver.findElement(byTxtAccountLogin).getAttribute("value");
    }
    public String getValuePassword(){
        return driver.findElement(byTxtPasswordLogin).getAttribute("value");
    }
}
