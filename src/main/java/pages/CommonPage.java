package pages;

import base.BasePage;
import components.TopBarNavigation;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {

    protected TopBarNavigation topBarNavigation;

    public CommonPage(WebDriver driver) {
        super(driver);
        System.out.println("CommonPage driver = " + driver);
        topBarNavigation = new TopBarNavigation(driver);
    }

    public LoginPage navigateLoginPage() {
        return topBarNavigation.navigateLoginPage();
    }

    public RegisterPage navigateRegisterPage() {
        return topBarNavigation.navigateRegisterPage();
    }
    public HistoryPage navigateHistoryPage() {
        return topBarNavigation.navigateHistoryPage();
    }
    public HomePage logOutPage(){return topBarNavigation.logOutPage();}
}
