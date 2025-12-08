package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarNavigation extends BasePage {

    private By byLnkLogin = By.xpath("//h3[text()='Đăng Nhập']");;
    private By byLnkRegister = By.xpath("//a[@href='/sign-up']");;
    private By byLnkAccount = By.xpath("//a[@href='/account']");;

    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateLoginPage() {
        click(byLnkLogin);
    }

    public void navigateRegisterPage() {
        click(byLnkRegister);
    }
    public void navigateHistoryPage() {
        click(byLnkAccount);
    }

}
