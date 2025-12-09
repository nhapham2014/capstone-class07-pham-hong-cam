package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {
    HomePage homePage;

    private By byTxtAccountLogin = By.id("taiKhoan");;
    private By byTxtPasswordLogin = By.id("matKhau");;
    private By byBtnLogin = By.xpath("//button[.='Đăng nhập']");;
    private By byLblLoginMsg = By.id("swal2-title");;
    private By byBtnClose = By.xpath("//button[contains(text(),'Đóng')]");

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
        clickClose();
//        waitForPageLoaded();
//        homePage = new HomePage(driver);
//        return homePage;
    }
}
