package testcases.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class TC0_LoginTest extends BaseTest {

    //class variable
    LoginPage loginPage;
    HomePage homePage;
    private void navigateToLoginPage(){
        homePage = new HomePage(driver);
        driver.get("https://demo1.cybersoft.edu.vn");
        loginPage = homePage.navigateLoginPage();

    }

    @Test
    public void TC01_testValidLogin() {
        navigateToLoginPage();

        loginPage.login("cam","123456");

        //Step 6: Verify login successully
        //VP1: "Đăng nhập thành công" message displays
        String actualLoginMsg = loginPage.getMessage();
        Assert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");

        //VP2: User profile name displays
        String actualDisplayName = homePage.getUserProfileName();
        Assert.assertEquals(actualDisplayName, "Pham Hong Cam", "User Profile name");

    }
    @Test
    public void TC02_testInvalidWithEmptyUsername(){
        navigateToLoginPage();
        loginPage.enterPassword("Diqit0505@");
        loginPage.clickLogin();
        String actualErrorUsername = loginPage.getErrorRequireUsername();
        Assert.assertEquals(actualErrorUsername, "Đây là trường bắt buộc !", "Không hiển thị lỗi khi không nhập tài khoản");
    }
    @Test
    public void TC03_testInvalidWithEmptyPassword(){
        navigateToLoginPage();
        loginPage.enterAccount("cam0592");
        loginPage.clickLogin();
        String actualErrorPassword = loginPage.getErrorRequirePassword();
        Assert.assertEquals(actualErrorPassword, "Đây là trường bắt buộc !", "Không hiển thị lỗi khi không nhập password");
    }
    @Test
    public void TC04_testInvalidWithIncorrectFormatPassword(){
        navigateToLoginPage();
        loginPage.enterAccount("cam0592");
        loginPage.enterPassword("123");
        loginPage.clickLogin();
        String actualErrorPassword = loginPage.getErrorRequirePassword();
        Assert.assertEquals(actualErrorPassword, "Mật khẩu phải có ít nhất 6 kí tự !", "Không hiển thị lỗi khi nhập password không đủ 6 kí tự trở lên");
    }
    @Test
    public void TC05_testInvalidWithIncorrectUsername(){
        navigateToLoginPage();
        loginPage.enterAccount("cam852");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        String actualMsgError = loginPage.getErrorMessage();
        Assert.assertEquals(actualMsgError, "Tài khoản hoặc mật khẩu không đúng!", "Không hiển thị lỗi khi nhập sai password hoặc username");
    }
    @Test
    public void TC06_verifyDefaultValueAfterClickRememeberForMe(){
        navigateToLoginPage();
        loginPage.enterAccount("cam0592");
        loginPage.enterPassword("Diqit0505@");
        loginPage.clickRememberChkbx();
        loginPage.clickLogin();
        homePage.logOutPage();
        homePage.navigateLoginPage();
        String actualAccountValue = loginPage.getValueAccount();
        String actualPasswordValue = loginPage.getValuePassword();
        Assert.assertEquals(actualAccountValue,"cam0592");
        Assert.assertEquals(actualPasswordValue,"Diqit0505@");
    }
    @Test
    public void click(){
        navigateToLoginPage();
        loginPage.clickRememberChkbx();
    }

}
