package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

public class TC_CinemaTest extends BaseTestWithLogin {
    HomePage homePage;
    LoginPage loginPage;
    SeatPage seatPage;

    @Test (dataProvider = "verifyListCinemaBrandData", dataProviderClass = dataproviders.CinemaDataProvider.class)
    public void TC01_verifyListCinemaWhenSelectCinemaLogo(String cinemaBrand) {
        homePage = new HomePage(driver);
        //Step 1: Select a logo of cinema
        ExtentReportManager.info("Step 1: Select a logo of cinema");
        LOG.info("Step 1: Select a logo of cinema");
        homePage.selectCinemaLogo(cinemaBrand);
        //Step 2: Verify list cinema
        ExtentReportManager.info("Step 2: Verify list cinema ");
        LOG.info("Step 2: Verify list cinema ");
        ExtentReportManager.verifyTrue(homePage.isCinemaBelongToSystem(cinemaBrand),
                "Có rạp KHÔNG thuộc hệ thống!",driver);
        Assert.assertTrue(homePage.isCinemaBelongToSystem(cinemaBrand),
                "Có rạp KHÔNG thuộc hệ thống!");

    }

    @Test (dataProvider = "verifyShowTimeInFutureData", dataProviderClass = dataproviders.CinemaDataProvider.class)
    public void TC02_verifyAllShowTimeLargerCurrent(String cinemaBrand, String cinemaBranch) {
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
        //Step 3: Verify all show time larger than current
        ExtentReportManager.info("Step 3: Verify all show time larger than current");
        LOG.info("Step 3: Verify all show time larger than current");
        ExtentReportManager.verifyTrue(homePage.isShowTimeInFuture(), "Ngày giờ suất chiếu phải lớn hơn hiện tại",driver);
        Assert.assertTrue(homePage.isShowTimeInFuture(), "Ngày giờ suất chiếu phải lớn hơn hiện tại");

    }

    @Test (dataProvider = "verifyForShowTimeOverLap", dataProviderClass = dataproviders.CinemaDataProvider.class)
    public void TC03_verifyForShowTimeOverLap(String cinemaBrand, String cinemaBranch, String movieName) {
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
        ExtentReportManager.verifyTrue(homePage.isShowtimeListUnique(movieName), "Lỗi: có suất chiếu của phim trùng nhau",driver);
        Assert.assertTrue(homePage.isShowtimeListUnique(movieName), "Lỗi: có suất chiếu của phim trùng nhau");

    }

    @Test (dataProvider = "navigateToSeatPageAfterSelectShowTime", dataProviderClass = dataproviders.CinemaDataProvider.class)
    public void TC04_navigateToSeatPageAfterSelectShowTime(String cinemaBrand,String cinemaBranch, String movieName, String showDate, String showTime) {
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
        seatPage = homePage.selectShowTime(movieName, showDate, showTime);
        //Step 4: Verify navigate to seat page
        ExtentReportManager.info("Step 4: Verify navigate to seat page");
        LOG.info("Step 4: Verify navigate to seat page");
        ExtentReportManager.verifyTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase/"),"Không điều hướng đến trang chọn ghế",driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase/"),"Không điều hướng đến trang chọn ghế");
    }

    @Test (dataProvider = "verifyOnTicketInfoAtSeatPage", dataProviderClass = dataproviders.CinemaDataProvider.class)
    public void TC05_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketAtListCinema(String cinemaBrand, String cinemaBranch, String movieName, String showDate, String showTime, String cinemaAddress) {
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
        seatPage = homePage.selectShowTime(movieName, showDate, showTime);
        //Step 4: Verify values on ticket at seat page
        ExtentReportManager.info("Step 4: Verify information on ticket at seat page");
        LOG.info("Step 4: Verify information on ticket at seat page");
        //VP1: Verify the movie name
        ExtentReportManager.info("VP1: Verify the movie name");
        LOG.info("VP1: Verify the movie name");
        ExtentReportManager.verifyEqualsString(seatPage.getMovieName(), movieName, "Hiển thị không đúng tên phim",driver);
        softAssert.assertEquals(seatPage.getMovieName(), movieName, "Hiển thị không đúng tên phim");
        //VP2: Verify the cinema branch
        ExtentReportManager.info("VP2: Verify the cinema branch");
        LOG.info("VP2: Verify the cinema branch");
        ExtentReportManager.verifyEqualsString(seatPage.getNameCinemaBranch(), cinemaBranch, "Không hiển thị đúng tên cụm rạp",driver);
        softAssert.assertEquals(seatPage.getNameCinemaBranch(), cinemaBranch, "Không hiển thị đúng tên cụm rạp");
        //VP3: Verify the address of cinema
        ExtentReportManager.info("VP3: Verify the address of cinema");
        LOG.info("VP3: Verify the address of cinema");
        ExtentReportManager.verifyEqualsString(seatPage.getAddressCinema(), cinemaAddress, "Không hiển thị đúng địa chỉ cụm rạp",driver);
        softAssert.assertEquals(seatPage.getAddressCinema(), cinemaAddress, "Không hiển thị đúng địa chỉ cụm rạp");
        //VP4: Verify the date
        ExtentReportManager.info("VP4: Verify the date");
        LOG.info("VP4: Verify the date");
        ExtentReportManager.verifyEqualsString(seatPage.getDateOfShowTime(), showDate, "Không hiển thị đúng ngày chiếu",driver);
        softAssert.assertEquals(seatPage.getDateOfShowTime(), showDate, "Không hiển thị đúng ngày chiếu");
        //VP5: Verify the time
        ExtentReportManager.info("VP5: Verify the time");
        LOG.info("VP5: Verify the time");
        ExtentReportManager.verifyEqualsString(seatPage.getTimeOfShowTime(), showTime, "Không hiển thị đúng giờ chiếu",driver);
        softAssert.assertEquals(seatPage.getTimeOfShowTime(), showTime, "Không hiển thị đúng giờ chiếu");
        softAssert.assertAll();
    }
}
