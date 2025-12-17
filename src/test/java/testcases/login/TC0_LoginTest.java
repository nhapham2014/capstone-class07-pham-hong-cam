package testcases.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import reports.ExtentReportManager;

public class TC0_LoginTest extends BaseTest {

    //class variable
    LoginPage loginPage;
    HomePage homePage;
    final String name ="cam";
    final String errorName ="cam223";
    final String password ="123456";
    final String incorrectPassword ="123";
    final String urlSignUp ="https://demo1.cybersoft.edu.vn/sign-up";


    @Test
    public void TC01_testValidLogin() {
        homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Login with the exist account
        ExtentReportManager.info("Step 2: Login with the exist account");
        LOG.info("Step 2: Login with the exist account");
        loginPage.login(name, password);
        //Step 3: Verify login successully
        ExtentReportManager.info("Step 3: Verify login successully");
        LOG.info("Step 3: Verify login successully");
        //VP1: "Đăng nhập thành công" message display
        ExtentReportManager.info("VP1: \"Đăng nhập thành công\" message display");
        LOG.info("VP1: \"Đăng nhập thành công\" message display");
        String actualLoginMsg = loginPage.getMessage();
        softAssert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");
        //VP2: User profile name displays
        ExtentReportManager.info("VP2: User profile name displays");
        LOG.info("VP2: User profile name displays");
        String actualDisplayName = homePage.getUserProfileName();
        softAssert.assertEquals(actualDisplayName, "Pham Hong Cam", "User Profile name");
        softAssert.assertAll();

    }

    @Test
    public void TC02_testInvalidWithEmptyUsername() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Input value in Password field
        ExtentReportManager.info("Step 2: Input value in Password field");
        LOG.info("Step 2: Input value in Password field");
        loginPage.enterPassword(password);
        //Step 3: click on the Login button
        ExtentReportManager.info("Step 3: click on the Login button");
        LOG.info("Step 3: click on the Login button");
        loginPage.clickLoginButtonWithErrorCase();
        //Step 3: verify the error message
        ExtentReportManager.info("Step 3: verify the error message");
        LOG.info("Step 3: verify the error message");
        String actualErrorUsername = loginPage.getErrorRequireUsername();
        Assert.assertEquals(actualErrorUsername, "Đây là trường bắt buộc !", "Không hiển thị lỗi khi không nhập tài khoản");
    }

    @Test
    public void TC03_testInvalidWithEmptyPassword() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Input value in Account field
        ExtentReportManager.info("Step 2: Input value in Account field");
        LOG.info("Step 2: Input value in Account field");
        loginPage.enterAccount(name);
        //Step 3: click on the Login button
        ExtentReportManager.info("Step 3: click on the Login button");
        LOG.info("Step 3: click on the Login button");
        loginPage.clickLoginButtonWithErrorCase();
        //Step 3: verify the error message
        ExtentReportManager.info("Step 3: verify the error message");
        LOG.info("Step 3: verify the error message");
        String actualErrorPassword = loginPage.getErrorRequirePassword();
        Assert.assertEquals(actualErrorPassword, "Đây là trường bắt buộc !", "Không hiển thị lỗi khi không nhập password");
    }

    @Test
    public void TC04_testInvalidWithIncorrectFormatPassword() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Input value in Account field
        ExtentReportManager.info("Step 2: Input value in Account field");
        LOG.info("Step 2: Input value in Account field");
        loginPage.enterAccount(name);
        //Step 3: Input the password with incorrect format
        ExtentReportManager.info("Step 3: Input the password with incorrect format");
        LOG.info("Step 3: Input the password with incorrect format");
        loginPage.enterPassword(incorrectPassword);
        //Step 4: click on the Login button
        ExtentReportManager.info("Step 4: click on the Login button");
        LOG.info("Step 4: click on the Login button");
        loginPage.clickLoginButtonWithErrorCase();
        //Step 5: verify the error message
        ExtentReportManager.info("Step 5: verify the error message");
        LOG.info("Step 5: verify the error message");
        String actualErrorPassword = loginPage.getErrorRequirePassword();
        Assert.assertEquals(actualErrorPassword, "Mật khẩu phải có ít nhất 6 kí tự !", "Không hiển thị lỗi khi nhập password không đủ 6 kí tự trở lên");
    }

    @Test
    public void TC05_testInvalidWithIncorrectUsername() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Input the incorrect value in Account field
        ExtentReportManager.info("Step 2: Input the incorrect value in Account field");
        LOG.info("Step 2: Input the incorrect value in Account field");
        loginPage.enterAccount(errorName);
        //Step 3: Input the incorrect value in Password field
        ExtentReportManager.info("Step 3: Input the incorrect value in Password field");
        LOG.info("Step 3: Input the incorrect value in Password field");
        loginPage.enterPassword(password);
        //Step 4: click on the Login button
        ExtentReportManager.info("Step 4: click on the Login button");
        LOG.info("Step 4: click on the Login button");
        loginPage.clickLoginButtonWithErrorCase();
        //Step 5: verify the error message
        ExtentReportManager.info("Step 5: verify the error message");
        LOG.info("Step 5: verify the error message");
        String actualMsgError = loginPage.getErrorMessage();
        Assert.assertEquals(actualMsgError, "Tài khoản hoặc mật khẩu không đúng!", "Không hiển thị lỗi khi nhập sai password hoặc username");
    }

    @Test
    public void TC06_verifyDefaultValueAfterClickRememeberForMe() {
        homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Input the correct value in Account field
        ExtentReportManager.info("Step 2: Input the correct value in Account field");
        LOG.info("Step 2: Input the correct value in Account field");
        loginPage.enterAccount(name);
        //Step 3: Input the correct value in Password field
        ExtentReportManager.info("Step 3: Input the correct value in Password field");
        LOG.info("Step 3: Input the correct value in Password field");
        loginPage.enterPassword(password);
        //Step 4: Click on the Remember checkbox
        ExtentReportManager.info("Step 4: Click on the Remember checkbox");
        LOG.info("Step 4: Click on the Remember checkbox");
        loginPage.clickRememberChkbx();
        //Step 5: Click on the Login button
        ExtentReportManager.info("Step 5: Click on the Login button");
        LOG.info("Step 5: Click on the Login button");
        loginPage.clickLogin();
        //Step 6: Log out
        ExtentReportManager.info("Step 6: Log out");
        LOG.info("Step 6: Log out");
        homePage.logOutPage();
        //Step 7: Navigate to Login page
        ExtentReportManager.info("Step 7: Navigate to Login page");
        LOG.info("Step 7: Navigate to Login page");
        homePage.navigateLoginPage();
        //Step 8: verify the default value in Account and Password fields
        ExtentReportManager.info("Step 8: verify the default value in Account and Password fields");
        LOG.info("Step 8: verify the default value in Account and Password fields");
        String actualAccountValue = loginPage.getValueAccount();
        String actualPasswordValue = loginPage.getValuePassword();
        softAssert.assertEquals(actualAccountValue, name);
        softAssert.assertEquals(actualPasswordValue, password);
        softAssert.assertAll();
    }
    @Test
    public void TC07_verifyNavigateToRegisterPageWhenClickSignUpLink() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Login page
        ExtentReportManager.info("Step 1: Navigate to Login page");
        LOG.info("Step 1: Navigate to Login page");
        loginPage=homePage.navigateLoginPage();
        //Step 2: Click on the Sign Up link
        ExtentReportManager.info("Step 2: Click on the Sign Up link");
        LOG.info("Step 2: Click on the Sign Up link");
        loginPage.clickSignUpLink();
        //Step 3: verify navigate to Register page successfully
        ExtentReportManager.info("Step 3: verify navigate to Register page successfully");
        LOG.info("Step 3: verify navigate to Register page successfully");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, urlSignUp, "Không điều hướng đến trang Đăng Ký");
    }

}
