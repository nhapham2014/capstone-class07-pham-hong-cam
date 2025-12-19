package testcases.register;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import reports.ExtentReportManager;
import java.util.UUID;

public class TC_RegisterTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;

    final String password = "Diqit0505@";
    final String confirmPass = "Diqit0505@";
    final String errorConfirmPass = "Diqit0506@";
    final String fullName = "Pham Thanh Nha";

    @Test
    public void TC01_testRegisterSuccess() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Enter valid values on textbox
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        loginPage = registerPage.registerAccount(newAcount, password, confirmPass, fullName, newAcount + "@example.com");
        //Step 3: Verify register successfully
        ExtentReportManager.info("Step 3: Verify register successfully");
        ExtentReportManager.info("VP1: \"Đăng ký thành công\" message displays");
        LOG.info("Step 3: Verify register successfully");
        LOG.info("VP1: \"Đăng ký thành công\" message displays");
        //VP1: "Đăng ký thành công" message displays
        String actualMsg = registerPage.getMessage();
        softAssert.assertEquals(actualMsg, "Đăng ký thành công", "Register message");

        //VP2: Verify new account login successfully
        ExtentReportManager.info("VP2: Verify new account login successfully");
        LOG.info("VP2: Verify new account login successfully");
        registerPage.clickClose();
        ;
        registerPage.clickLoginLink();
        loginPage.login(newAcount, password);
        ExtentReportManager.info("VP1: \"Đăng nhập thành công\" message displays");
        LOG.info("VP1: \"Đăng nhập thành công\" message displays");
        //VP1: "Đăng nhập thành công" message displays
        String actualLoginMsg = loginPage.getMessage();
        softAssert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");
        ExtentReportManager.info("VP2: User profile name displays");
        LOG.info("VP2: User profile name displays");
        //VP2: User profile name displays
        String actualDisplayName = homePage.getUserProfileName();
        softAssert.assertEquals(actualDisplayName, fullName, "User Profile name");
        softAssert.assertAll();
    }

    @Test
    public void TC02_verifyRegisterWithEmptyValue() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Click Register button with empty values
        ExtentReportManager.info("Step 2: Click Register button with empty values");
        LOG.info("Step 2: Click Register button with empty values");
        registerPage.clickRegisterWithError();
        //Step 3: Verify error messages at all fields
        ExtentReportManager.info("Step 3: Verify error messages at all fields");
        LOG.info("Step 3: Verify error messages at all fields");
        //VP1: Verify error message at Account field
        ExtentReportManager.info("VP1: Verify error message at Account field");
        LOG.info("VP1: Verify error message at Account field");
        String actualErrorAccount = registerPage.getErrorAccount();
        softAssert.assertEquals(actualErrorAccount, "Đây là trường bắt buộc !", "Không hiển thị đúng lỗi tại field Tài khoản");
        //VP2: Verify error message at Password field
        ExtentReportManager.info("VP2: Verify error message at Password field");
        LOG.info("VP2: Verify error message at Password field");
        String actualErrorPassword = registerPage.getErrorPassword();
        softAssert.assertEquals(actualErrorPassword, "Đây là trường bắt buộc !", "Không hiển thị đúng lỗi tại field Mật khẩu");
        //VP3: Verify error message at Confirm Password field
        ExtentReportManager.info("VP3: Verify error message at Confirm Password field");
        LOG.info("VP3: Verify error message at Confirm Password field");
        String actualErrorConfirmPassword = registerPage.getErrorConfirmPassword();
        softAssert.assertEquals(actualErrorConfirmPassword, "Đây là trường bắt buộc !", "Không hiển thị đúng lỗi tại field Xác nhận mật khẩu");
        //VP4: Verify error message at Name field
        ExtentReportManager.info("VP4: Verify error message at Name field");
        LOG.info("VP4: Verify error message at Name field");
        String actualErrorName = registerPage.getErrorName();
        softAssert.assertEquals(actualErrorName, "Đây là trường bắt buộc !", "Không hiển thị đúng lỗi tại field Họ và tên");
        //VP5: Verify error message at Email field
        ExtentReportManager.info("VP5: Verify error message at Email field");
        LOG.info("VP5: Verify error message at Email field");
        String actualErrorEmail = registerPage.getErrorEmail();
        softAssert.assertEquals(actualErrorEmail, "Đây là trường bắt buộc !", "Không hiển thị đúng lỗi tại field Email");
        softAssert.assertAll();
    }

    @Test
    public void TC04_verifyRegisterWithMismatchedPassword() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Enter mismatched Password and Confirm Password
        ExtentReportManager.info("Step 2: Enter mismatched Password and Confirm Password");
        LOG.info("Step 2: Enter mismatched Password and Confirm Password");
        registerPage.registerInvalid("", password, errorConfirmPass, "", "");
        //Step 3: Verify error message at Confirm Password field
        ExtentReportManager.info("Step 3: Verify error message at Confirm Password field");
        LOG.info("Step 3: Verify error message at Confirm Password field");
        String actualErrorConfirmPassword = registerPage.getErrorConfirmPassword();
        Assert.assertEquals(actualErrorConfirmPassword, "Mật khẩu không khớp !", "Không hiển thị đúng lỗi tại field Xác nhận mật khẩu");
    }

    @Test
    public void TC05_verifyRegisterWithExistingAccount() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Enter existing account
        ExtentReportManager.info("Step 2: Enter existing account");
        LOG.info("Step 2: Enter existing account");
        registerPage.registerInvalid("cam0592", "Diqit0505@", "Diqit0505@", "Pham Thanh Nha", "cam997789@gmail.com");
        //Step 3: Verify error message for existing account
        ExtentReportManager.info("Step 3: Verify error message for existing account");
        LOG.info("Step 3: Verify error message for existing account");
        String actualErrorMsg = registerPage.getErrorText();
        Assert.assertEquals(actualErrorMsg, "Tài khoản đã tồn tại!", "Không hiển thị đúng lỗi tài khoản đã tồn tại");
    }

    @Test
    public void TC06_verifyRegisterWithExistingEmail() {
        homePage = new HomePage(driver);
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Enter existing email
        ExtentReportManager.info("Step 2: Enter existing email");
        LOG.info("Step 2: Enter existing email");
        registerPage.registerInvalid(newAcount, "Diqit0505@", "Diqit0505@", "Pham Thanh Nha", "cam@gmail.com");
        //Step 3: Verify error message for existing email
        ExtentReportManager.info("Step 3: Verify error message for existing email");
        LOG.info("Step 3: Verify error message for existing email");
        String actualErrorMsg = registerPage.getErrorText();
        Assert.assertEquals(actualErrorMsg, "Email đã tồn tại!", "Không hiển thị đúng lỗi email đã tồn tại");
    }

    @Test
    public void TC07_verifyRegisterWithWeakPassword() {
        homePage = new HomePage(driver);
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Enter weak password
        ExtentReportManager.info("Step 2: Enter weak password");
        LOG.info("Step 2: Enter weak password");
        registerPage.registerInvalid("newAcount", "123", "123", "Pham Thanh Nha", newAcount + "@example.com");
        //Step 3: Verify error message for weak password
        ExtentReportManager.info("Step 3: Verify error message for weak password");
        LOG.info("Step 3: Verify error message for weak password");
        String actualErrorMsg = registerPage.getErrorPassword();
        Assert.assertEquals(actualErrorMsg, "Mật khẩu phải có ít nhất 6 kí tự !", "Không hiển thị đúng lỗi mật khẩu yếu");
    }

    @Test
    public void TC08_navigateToLoginPageFromRegisterPage() {
        homePage = new HomePage(driver);
        //Step 1: Navigate to Register page
        ExtentReportManager.info("Step 1: Navigate to Register page");
        LOG.info("Step 1: Navigate to Register page");
        registerPage = homePage.navigateRegisterPage();
        //Step 2: Click on Login link
        ExtentReportManager.info("Step 2: Click on Login link");
        LOG.info("Step 2: Click on Login link");
        loginPage = registerPage.clickLoginLink();
        //Step 3: Verify navigate to Login page successfully
        ExtentReportManager.info("Step 3: Verify navigate to Login page successfully");
        LOG.info("Step 3: Verify navigate to Login page successfully");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/sign-in"), "Không điều hướng đến trang Đăng nhập thành công");
    }
}
