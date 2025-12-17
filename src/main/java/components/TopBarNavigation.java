package components;

import base.BasePage;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;

public class TopBarNavigation extends BasePage {
    LoginPage loginPage;
    RegisterPage registerPage;
    HistoryPage historyPage;
    HomePage homePage;

    private By byLnkLogin = By.xpath("//h3[text()='Đăng Nhập']");;
    private By byLnkRegister = By.xpath("//a[@href='/sign-up']");;
    private By byLnkAccount = By.xpath("//a[@href='/account']");;
    private By byLnkLogout = By.xpath("//a[h3[contains(text(),'Đăng xuất')]]");
    private By byBtnAgree = By.xpath("//button[contains(text(),'Đồng ý')]");
    private By byBtnOk = By.xpath("//button[contains(text(),'OK')]");

    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public LoginPage navigateLoginPage() {
        click(byLnkLogin);
        waitUtil.waitForPageLoaded();
        loginPage = new LoginPage(driver);
        return loginPage;

    }

    public RegisterPage navigateRegisterPage() {
        click(byLnkRegister);
        waitUtil.waitForPageLoaded();
        registerPage = new RegisterPage(driver);
        return registerPage;
    }
    public HistoryPage navigateHistoryPage() {
        click(byLnkAccount);
        waitUtil.waitForPageLoaded();
        historyPage = new HistoryPage(driver);
        return historyPage;
    }
    public HomePage logOutPage(){
        click(byLnkLogout);
        click(byBtnAgree);
        click(byBtnOk);
        waitUtil.waitForPageLoaded();
         homePage= new HomePage(driver);
        return homePage;
    }

}
