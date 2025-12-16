package base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;

public class BaseTestWithLogin extends BaseTest{
    @Override
    protected boolean needLogin() {
        return true;
    }
}
