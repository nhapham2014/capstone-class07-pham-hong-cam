package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

public class TC_FilterTest extends BaseTestWithLogin {
    HomePage homePage;
    SeatPage seatPage;

    @Test
    public void TC01_cinemaAndDateHaveOnlyDefaultOptionWhenMovieNotSelected() {
        homePage = new HomePage(driver);
        int cinemaCount = homePage.getDefaultValueCinemaOptionCount();
        int dateCount = homePage.getDefaultValueShowtimeOptionsCount();
        Assert.assertEquals(cinemaCount, 1,
                "Dropdown Rạp phải chỉ có 1 option mặc định khi chưa chọn Phim");
        Assert.assertEquals(dateCount, 1,
                "Dropdown Thời gian chiếu phải chỉ có 1 option mặc định khi chưa chọn Phim");

    }
    @Test
    public void TC02_changeFilm_cinemaAndShowtimeMustReset() {
        homePage = new HomePage(driver);

        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie("AVATAR 2");
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema("CGV - Golden Plaza");
        //Step 3: Select date time
        ExtentReportManager.info("Step 3: Select date time");
        LOG.info("Step 3: Select date time");
        homePage.selectDate("15/10/2021 ~ 08:42");
        // Step 4: Change to another film
        ExtentReportManager.info("Step 4: Change to another film");
        LOG.info("Step 4: Change to another film");
        homePage.selectMovie("Thor 6");
        // Step 5: Verify cinema and showtime are reset
        ExtentReportManager.info("Step 5: Verify cinema and showtime are reset");
        LOG.info("Step 5: Verify cinema and showtime are reset");
        Assert.assertTrue(homePage.cinemaIsReset(),
                "Sau khi đổi phim, dropdown Rạp phải reset về mặc định");
        Assert.assertTrue(homePage.showtimeIsReset(),
                "Sau khi đổi phim, dropdown Ngày giờ phải reset về mặc định");

    }
    @Test
    public void TC03_changeCinema_showtimeMustReset() {
        homePage = new HomePage(driver);
        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie("AVATAR 2");
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema("CGV - Golden Plaza");
        //Step 3: Select date time
        ExtentReportManager.info("Step 3: Select date time");
        LOG.info("Step 3: Select date time");
        homePage.selectDate("15/10/2021 ~ 08:42");

        // Step 4: Change to another cinema
        ExtentReportManager.info("Step 4: Change to another cinema");
        LOG.info("Step 4: Change to another cinema");
        homePage.selectCinema("MegaGS - Cao Thắng");

        // Step 5: Verify showtime is reset
        ExtentReportManager.info("Step 5: Verify showtime is reset");
        LOG.info("Step 5: Verify showtime is reset");
        Assert.assertTrue(homePage.showtimeIsReset(),
                "Sau khi đổi phim, dropdown Ngày giờ phải reset về mặc định");
    }
    @Test
    public void TC04_selectMovie_thenCinemaLoaded() {
        homePage = new HomePage(driver);
        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie("AVATAR 2");
        //Step 2: Verify cinema dropdown is loaded
        ExtentReportManager.info("Step 2: Verify cinema dropdown is loaded");
        LOG.info("Step 2: Verify cinema dropdown is loaded");
        int cinemaCount = homePage.getCinemaOptionsAfterSelectMovie();
        Assert.assertTrue(cinemaCount > 1,
                "Sau khi chọn phim, dropdown Rạp phải load danh sách rạp");
    }
    @Test
    public void TC05_selectCinema_thenShowtimeLoaded() {
        homePage = new HomePage(driver);
        // Step 1: Select film
        ExtentReportManager.info("Step 1: Select film");
        LOG.info("Step 1: Select film");
        homePage.selectMovie("AVATAR 2");
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema("CGV - Golden Plaza");
        //Step 3: Verify showtime dropdown is loaded
        ExtentReportManager.info("Step 3: Verify showtime dropdown is loaded");
        LOG.info("Step 3: Verify showtime dropdown is loaded");
        int showtimeCount = homePage.getShowTimeOptionsAfterSelectMovie();
        Assert.assertTrue(showtimeCount > 1,
                "Dropdown Giờ chiếu phải load dữ liệu sau khi chọn Rạp");
    }
    @Test
    public void TC05_displayWarmPopupWhenClickBuyTicketWithoutSelectAll() {
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
        homePage.selectMovie("AVATAR 2");
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
        homePage.selectMovie("AVATAR 2");
        //Step 2: Select cinema
        ExtentReportManager.info("Step 2: Select cinema");
        LOG.info("Step 2: Select cinema");
        homePage.selectCinema("CGV - Golden Plaza");
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
        seatPage = homePage.buyTicketAtFilterSection("AVATAR 2","CGV - Golden Plaza","15/10/2021 ~ 08:42");
        //Step 2: Verify navigate to seat page
        ExtentReportManager.info("Step 2: Verify navigate to seat page");
        LOG.info("Step 2: Verify navigate to seat page");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase"),
                "Không chuyển sang màn hình chọn ghế sau khi bấm Mua Vé Ngay");
    }
    @Test
    public void TC09_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketByFilter(){
        homePage = new HomePage(driver);
        //Step 1: Select movie, cinema branch, show time at the Filter section
        ExtentReportManager.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        LOG.info("Step 1: Select movie, cinema branch, show time at the Filter section ");
        seatPage = homePage.buyTicketAtFilterSection("AVATAR 2","CGV - Golden Plaza","15/10/2021 ~ 08:42");
        //Step 2: verify values on ticket at Seat page
        ExtentReportManager.info("Step 2: verify values on ticket at Seat page");
        LOG.info("Step 2: verify values on ticket at Seat page");
        //VP1: verify the movie name
        ExtentReportManager.info("VP1: verify the movie name");
        LOG.info("VP1: verify the movie name");
        Assert.assertEquals(seatPage.getMovieName(),"AVATAR 2", "Hiển thị không đúng tên phim");
        //VP1: verify the movie name
        ExtentReportManager.info("VP1: verify the movie name");
        LOG.info("VP1: verify the movie name");
        Assert.assertEquals(seatPage.getNameCinemaBranch(),"CGV - Golden Plaza", "Không hiển thị đúng tên cụm rạp");
        //VP2: verify the address cinema
        ExtentReportManager.info("VP2: verify the address cinema");
        LOG.info("VP2: verify the address cinema");
        Assert.assertEquals(seatPage.getAddressCinema(), "Tầng 4, Trung tâm thương mại Golden Plaza, 922 Nguyễn Trãi, P. 14, Q. 5", "Không hiển thị đúng địa chỉ cụm rạp");
        //VP3: verify the date of show time
        ExtentReportManager.info("VP3: verify the date of show time");
        LOG.info("VP3: verify the date of show time");
        Assert.assertEquals(seatPage.getDateOfShowTime(),"15-10-2021", "Không hiển thị đúng ngày chiếu");
        //VP4: verify the time of show time
        ExtentReportManager.info("VP4: verify the time of show time");
        LOG.info("VP4: verify the time of show time");
        Assert.assertEquals(seatPage.getTimeOfShowTime(),"08:42", "Không hiển thị đúng giờ chiếu");
    }
}
