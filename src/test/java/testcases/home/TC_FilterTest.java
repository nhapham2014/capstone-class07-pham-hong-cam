package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

import java.time.LocalDateTime;

public class TC_FilterTest extends BaseTestWithLogin {
    HomePage homePage;
    SeatPage seatPage;
    final String movieName = "AVATAR 2";
    final String cinemaName = "CGV - Golden Plaza";
    final String showtime = "15/10/2021 ~ 08:42";
    final String dateOfShowtime = "15/10/2021";
    final String timeOfShowtime = "08:42";
    final String otherMovieName = "Man of Steel";


    @Test
    public void TC01_cinemaAndDateHaveOnlyDefaultOptionWhenMovieNotSelected() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        int cinemaCount = homePage.getDefaultValueCinemaOptionCount();
        int dateCount = homePage.getDefaultValueShowtimeOptionsCount();
        softAssert.assertEquals(cinemaCount, 1,
                "Dropdown Rạp phải chỉ có 1 option mặc định khi chưa chọn Phim");
        softAssert.assertEquals(dateCount, 1,
                "Dropdown Thời gian chiếu phải chỉ có 1 option mặc định khi chưa chọn Phim");
        softAssert.assertAll();

    }
    @Test
    public void TC02_changeFilm_cinemaAndShowtimeMustReset() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);

        // Step 1: Select film test
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie(movieName);
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema(cinemaName);
        //Step 3: Select date time
        ExtentReportManager.info("Step 3: Select date time");
        LOG.info("Step 3: Select date time");
        homePage.selectDate(showtime);
        // Step 4: Change to another film
        ExtentReportManager.info("Step 4: Change to another film");
        LOG.info("Step 4: Change to another film");
        homePage.selectMovie(otherMovieName);
        // Step 5: Verify cinema and showtime are reset
        ExtentReportManager.info("Step 5: Verify cinema and showtime are reset");
        LOG.info("Step 5: Verify cinema and showtime are reset");
        softAssert.assertTrue(homePage.cinemaIsReset(),
                "Sau khi đổi phim, dropdown Rạp phải reset về mặc định");
        softAssert.assertTrue(homePage.showtimeIsReset(),
                "Sau khi đổi phim, dropdown Ngày giờ phải reset về mặc định");
        softAssert.assertAll();

    }

    @Test
    public void TC03_displayWarmPopupWhenClickBuyTicketWithoutSelectAll() {
        homePage = new HomePage(driver);
        // Step 1: Click 'Mua Vé Ngay' button without select any filter
        ExtentReportManager.info("Step 1: Click 'Mua Vé Ngay' button without select any filter");
        LOG.info("Step 1: Click 'Mua Vé Ngay' button without select any filter");
        homePage.clickBuyTicketExpectError();
        // Step 2: Verify warning popup displays
        ExtentReportManager.info("Step 2: Verify warning popup displays");
        LOG.info("Step 2: Verify warning popup displays");
        String actualPopupMsg = homePage.getWarningPopupMessage();
        Assert.assertEquals(actualPopupMsg, "Vui lòng chọn phim",
                "Popup message không đúng khi bấm Mua Vé Ngay mà chưa chọn phim");
    }
    @Test
    public void TC06_displayWarmPopupWhenClickBuyTicketWithoutSelectCinema() {
        homePage = new HomePage(driver);
        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie(movieName);
        // Step 2: Click 'Mua Vé Ngay' button without select showtime
        ExtentReportManager.info("Step 2: Click 'Mua Vé Ngay' button without select cinema");
        LOG.info("Step 2: Click 'Mua Vé Ngay' button without select cinema");
        homePage.clickBuyTicketExpectError();
        // Step 3: Verify warning popup displays
        ExtentReportManager.info("Step 3: Verify warning popup displays");
        LOG.info("Step 3: Verify warning popup displays");
        String actualPopupMsg = homePage.getWarningPopupMessage();
        Assert.assertEquals(actualPopupMsg, "Vui lòng chọn rạp",
                "Popup message không đúng khi bấm Mua Vé Ngay mà chưa chọn rạp");
    }
    @Test
    public void TC07_displayWarmPopupWhenClickBuyTicketWithoutSelectShowtime() {
        homePage = new HomePage(driver);
        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie(movieName);
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema(cinemaName);
        // Step 3: Click 'Mua Vé Ngay' button without select showtime
        ExtentReportManager.info("Step 3: Click 'Mua Vé Ngay' button without select showtime");
        LOG.info("Step 3: Click 'Mua Vé Ngay' button without select showtime");
        homePage.clickBuyTicketExpectError();
        // Step 4: Verify warning popup displays
        ExtentReportManager.info("Step 4: Verify warning popup displays");
        LOG.info("Step 4: Verify warning popup displays");
        String actualPopupMsg = homePage.getWarningPopupMessage();
        Assert.assertEquals(actualPopupMsg, "Vui lòng chọn ngày giờ chiếu",
                "Popup message không đúng khi bấm Mua Vé Ngay mà chưa chọn ngày giờ");
    }
    @Test
    public void TC08_navigateToSeatPageAfterClickBookTicketButton() {
        homePage = new HomePage(driver);
        //Step 1: Select movie, cinema branch, show time at the Filter section
        ExtentReportManager.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        LOG.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        seatPage=homePage.buyTicketByFilter(movieName,cinemaName,showtime);
        //Step 2: Verify navigate to seat page
        ExtentReportManager.info("Step 2: Verify navigate to seat page");
        LOG.info("Step 2: Verify navigate to seat page");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase"),
                "Không chuyển sang màn hình chọn ghế sau khi bấm Mua Vé Ngay");
    }
    @Test
    public void TC09_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketByFilter() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //Step 1: Select movie, cinema branch, show time at the Filter section
        ExtentReportManager.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        LOG.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        seatPage=homePage.buyTicketByFilter(movieName,cinemaName,showtime);
        //Step 2: verify values on ticket at Seat page
        ExtentReportManager.info("Step 2: verify values on ticket at Seat page");
        LOG.info("Step 2: verify values on ticket at Seat page");
        //VP1: verify the movie name
        ExtentReportManager.info("VP1: verify the movie name");
        LOG.info("VP1: verify the movie name");
        softAssert.assertEquals(seatPage.getMovieName(), movieName, "Hiển thị không đúng tên phim");
        //VP2: verify the movie name
        ExtentReportManager.info("VP2: verify the movie name");
        LOG.info("VP2: verify the movie name");
        softAssert.assertEquals(seatPage.getNameCinemaBranch(), cinemaName, "Không hiển thị đúng tên cụm rạp");
        //VP3: verify the show time
        ExtentReportManager.info("VP3: verify the date of show time");
        LOG.info("VP3: verify the date of show time");
        softAssert.assertEquals(seatPage.getDateOfShowTime(), dateOfShowtime, "Không hiển thị đúng ngày chiếu");
        softAssert.assertEquals(seatPage.getTimeOfShowTime(), timeOfShowtime, "Không hiển thị đúng giờ chiếu");
        softAssert.assertAll();

    }
}