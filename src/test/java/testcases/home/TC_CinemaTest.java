package testcases.home;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

public class TC_CinemaTest extends BaseTestWithLogin{
    HomePage homePage;
    LoginPage loginPage;
    SeatPage seatPage;
    final String cinemaBrand = "cgv";
    final String cinemaBranch ="CGV - Golden Plaza";
    final String movieName = "John Wick";
    final String showDate = "08-08-2021";
    final String showTime = "18:27";
    final String cinemaAddress = "Tầng 4, Trung tâm thương mại Golden Plaza, 922 Nguyễn Trãi, P. 14, Q. 5";


    //TC01: Verify list cinema after user select a cinema logo
    @Test
    public void TC01_verifyListCinemaWhenSelectCinemaLogo(){
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Verify list cinema
        ExtentReportManager.info("Step 2: Verify list cinema ");
        LOG.info("Step 2: Verify list cinema ");
            Assert.assertTrue(homePage.isCinemaBelongToSystem(cinemaBrand),
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
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch(cinemaBranch);
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
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch(cinemaBranch);
        //Step 3: Verify show time is unique
        ExtentReportManager.info("Step 3: Verify show time is unique");
        LOG.info("Step 3: Verify show time is unique");
        Assert.assertTrue(homePage.isShowtimeListUnique(movieName),"Lỗi: có suất chiếu của phim trùng nhau");

    }
    @Test
    public void TC04_navigateToSeatPageAfterSelectShowTime(){
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch(cinemaBranch);
        //Step 3: Select the show time
        ExtentReportManager.info("Step 3: Select the show time");
        LOG.info("Step 3: Select the show time");
        seatPage = homePage.selectShowTime(movieName,showDate,showTime);
        //Step 4: Verify navigate to seat page
        ExtentReportManager.info("Step 4: Verify navigate to seat page");
        LOG.info("Step 4: Verify navigate to seat page");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase/"));
    }

    @Test
    public void TC05_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketAtListCinema(){
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Select a cinema branch
        ExtentReportManager.info("Step 2: Select a cinema branch");
        LOG.info("Step 2: Select a cinema branch");
        homePage.selectCinemaBranch(cinemaBranch);
        //Step 3: Select the show time
        ExtentReportManager.info("Step 3: Select the show time");
        LOG.info("Step 3: Select the show time");
        seatPage = homePage.selectShowTime(movieName,showDate,showTime);
        //Step 4: Verify values on ticket at seat page
        ExtentReportManager.info("Step 4: Verify information on ticket at seat page");
        LOG.info("Step 4: Verify information on ticket at seat page");
        //VP1: Verify the movie name
         ExtentReportManager.info("VP1: Verify the movie name");
        LOG.info("VP1: Verify the movie name");
        softAssert.assertEquals(seatPage.getMovieName(),movieName, "Hiển thị không đúng tên phim");
        //VP2: Verify the cinema branch
        ExtentReportManager.info("VP2: Verify the cinema branch");
        LOG.info("VP2: Verify the cinema branch");
        softAssert.assertEquals(seatPage.getNameCinemaBranch(),cinemaBranch, "Không hiển thị đúng tên cụm rạp");
        //VP3: Verify the address of cinema
        ExtentReportManager.info("VP3: Verify the address of cinema");
        LOG.info("VP3: Verify the address of cinema");
        softAssert.assertEquals(seatPage.getAddressCinema(), cinemaAddress, "Không hiển thị đúng địa chỉ cụm rạp");
        //VP4: Verify the date
        ExtentReportManager.info("VP4: Verify the date");
        LOG.info("VP4: Verify the date");
        softAssert.assertEquals(seatPage.getDateOfShowTime(),showDate, "Không hiển thị đúng ngày chiếu");
        //VP5: Verify the time
        ExtentReportManager.info("VP5: Verify the time");
        LOG.info("VP5: Verify the time");
        softAssert.assertEquals(seatPage.getTimeOfShowTime(),showTime, "Không hiển thị đúng giờ chiếu");
        softAssert.assertAll();
    }
}
