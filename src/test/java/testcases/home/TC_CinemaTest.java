package testcases.home;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

import java.time.LocalDateTime;
import java.util.List;
public class TC_CinemaTest extends BaseTestWithLogin{
    HomePage homePage;
    LoginPage loginPage;
    SeatPage seatPage;
    //TC01: Verify list cinema after user select a cinema logo
    @Test
    public void TC01_verifyListCinemaWhenSelectCinemaLogo(){
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("cgv");
        //Step 2: Verify list cinema
        ExtentReportManager.info("Step 2: Verify list cinema ");
        LOG.info("Step 2: Verify list cinema ");
            Assert.assertTrue(homePage.isCinemaBelongToSystem("cgv"),
                    "Có rạp KHÔNG thuộc hệ thống!");

    }
    //TC02: Verfy list show time must larger than current
    @Test
    public void TC02_verifyAllShowTimeLargerCurrent(){
        homePage = new HomePage(driver);
        loginPage= new LoginPage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("bhd");
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch("BHD Star Cineplex - Phạm Hùng");
        //Step 3: Verify all show time larger than current
        ExtentReportManager.info("Step 3: Verify all show time larger than current");
        LOG.info("Step 3: Verify all show time larger than current");
        Assert.assertTrue(homePage.isShowTimeInFuture(),"Ngày giờ suất chiếu phải lớn hơn hiện tại");

    }
    @Test
    public void TC03_verifyForShowTimeOverLap(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("bhd");
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch("BHD Star Cineplex - Phạm Hùng");
        //Step 3: Verify show time is unique
        ExtentReportManager.info("Step 3: Verify show time is unique");
        LOG.info("Step 3: Verify show time is unique");
        Assert.assertTrue(homePage.isShowtimeListUnique("John Wick"),"Lỗi: có suất chiếu của phim trùng nhau");

    }
    @Test
    public void TC04_navigateToSeatPageAfterSelectShowTime(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.clickCinemaLogo("cgv");
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch("CGV - Pandora City");
        //Step 3: Select a show time
        ExtentReportManager.info("Step 3: Select a show time");
        LOG.info("Step 3: Select a show time");
        homePage.selectShowTime("John Wick","15-10-2020","13:10");
        //Step 4: Verify navigate to seat page
        ExtentReportManager.info("Step 4: Verify navigate to seat page");
        LOG.info("Step 4: Verify navigate to seat page");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase/"));
    }
    @Test
    public void TC(){
        homePage = new HomePage(driver);
        homePage.clickCinemaLogo("cgv");
        homePage.selectCinemaBranch("CGV - Golden Plaza");

    }

    @Test
    public void TC05_verifyTicketAtSeatPageWhenUserBookTicketAtListCinema(){
        homePage = new HomePage(driver);
        seatPage = new SeatPage(driver);
        homePage.bookTicketAtListTheater("cgv","CGV - Golden Plaza","AVATAR 2","15-10-2021", "08:42" );
        Assert.assertEquals(seatPage.getNameCinemaBranch(),"CGV - Golden Plaza", "Không hiển thị đúng tên cụm rạp");
        Assert.assertEquals(seatPage.getAddressCinema(), "Tầng 4, Trung tâm thương mại Golden Plaza, 922 Nguyễn Trãi, P. 14, Q. 5", "Không hiển thị đúng địa chỉ cụm rạp");
        Assert.assertEquals(seatPage.getDateOfShowTime(),"15-10-2021", "Không hiển thị đúng ngày chiếu");
        Assert.assertEquals(seatPage.getTimeOfShowTime(),"08:42", "Không hiển thị đúng giờ chiếu");
    }
}
