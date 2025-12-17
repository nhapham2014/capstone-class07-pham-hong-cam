package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends CommonPage {
    LoginPage loginPage;

    private By byTxtAccount = By.name("taiKhoan");;
    private By byTxtPassword = By.id("matKhau");;
    private By byTxtConfirmPassword = By.id("confirmPassWord");;
    private By byTxtName = By.id("hoTen");;
    private By byTxtEmail = By.id("email");;
    private By byBtnRegister = By.xpath("//button[.='Đăng ký']");;
    private By byMsgSuccess = By.id("swal2-title");;
    private By byTxtErrorAccount = By.xpath("//*[@id='taiKhoan-helper-text']");
    private By byTxtErrorPassword = By.xpath("//*[@id='matKhau-helper-text']");
    private By byTxtErrorConfirmPassword = By.xpath("//*[@id='confirmPassWord-helper-text']");
    private By byTxtErrorName = By.xpath("//*[@id='hoTen-helper-text']");
    private By byTxtErrorEmail = By.xpath("//*[@id='email-helper-text']");
    private By byTxtError = By.xpath("//div[@role='alert']/div[@class='MuiAlert-message']");
    private By byLinkLogin = By.xpath("//h3[contains(text(),'Đăng nhập')]");
    private By byBtnClose = By.xpath("//button[contains(text(),'Đóng')]");



    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String account) {
        sendKeys(byTxtAccount, account);
    }

    public void enterPassword(String password) {
        sendKeys(byTxtPassword, password);
    }

    public void enterConfirmPassword(String password) {
        sendKeys(byTxtConfirmPassword, password);
    }

    public void enterName(String name) {
        sendKeys(byTxtName, name);
    }

    public void enterEmail(String email) {
        sendKeys(byTxtEmail, email);
    }

    public void clickRegisterWithError() {
        click(byBtnRegister);
    }
    public LoginPage clickRegister(){
        click(byBtnRegister);
        waitUtil.waitForPageLoaded();
        loginPage = new LoginPage(driver);
        return loginPage;
    }

    public String getMessage() {
        return getText(byMsgSuccess);
    }
    public void clickClose(){
        click(byBtnClose);
    }
    public String getErrorAccount(){
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorAccount);
//        return getText(byTxtErrorAccount);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byTxtErrorAccount)
            );
            return error.getText().trim();
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "❌ Không hiển thị message lỗi cho case tài khoản đã tồn tại trong 5s"
            );
        }
    }
    public String getErrorPassword() {
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorPassword);
        return getText(byTxtErrorPassword);
    }
    public String getErrorConfirmPassword() {
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorConfirmPassword);
        return getText(byTxtErrorConfirmPassword);
    }
    public String getErrorName() {
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorName);
        return getText(byTxtErrorName);
    }
    public String getErrorEmail() {
        waitUtil.waitForVisibilityOfElementLocated(byTxtErrorEmail);
        return getText(byTxtErrorEmail);
    }
    public String getErrorText(){
        waitUtil.waitForVisibilityOfElementLocated(byTxtError);
        return getText(byTxtError);
    }
    public LoginPage clickLoginLink(){
        click(byLinkLogin);
        waitUtil.waitForPageLoaded();
        loginPage = new LoginPage(driver);
        return loginPage;
    }
    public LoginPage registerAccount(String account, String password, String confirmpassword, String name, String email){
        enterAccount(account);
        enterPassword(password);
        enterConfirmPassword(confirmpassword);
        enterName(name);
        enterEmail(email);
        return clickRegister();
    }
    public void registerInvalid(String account, String password, String confirmpassword, String name, String email){
        enterAccount(account);
        enterPassword(password);
        enterConfirmPassword(confirmpassword);
        enterName(name);
        enterEmail(email);
        clickRegisterWithError();
    }

}
