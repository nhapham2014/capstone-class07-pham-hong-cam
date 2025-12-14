package testcases.history;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import reports.ExtentReportManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TC_HistoryTest extends BaseTestWithLogin {
    HomePage homePage;
    SeatPage seatPage;
    HistoryPage historyPage;

    private void selectMovieAndShowTime() {
        HomePage homePage = new HomePage(driver);
        homePage.selectMovie("gái già lắm chiêu");
        homePage.selectCinema("CGV - Vincom Gò Vấp");
        homePage.selectDate("21/12/2021 ~ 16:00");
        seatPage = homePage.clickBuyTicket();

    }

    @Test
    public void TC01_verifyNumberBookingAtHistoryPage() {
        homePage = new HomePage(driver);
        // Step 1: Navigate to Booking History Page
        ExtentReportManager.info("Step 1: Navigate to Booking History Page ");
        LOG.info("Step 1: Navigate to Booking History Page");
        historyPage = homePage.navigateHistoryPage();
        // Step 2: verify number of booking
        ExtentReportManager.info("Step 2: verify number of booking");
        LOG.info("Step 2: verify number of booking");
        int numberBooking = historyPage.getNumberOfBookingInHistory();
        Assert.assertEquals(numberBooking, 32, "Hiển thị sai số lượng booking");
    }

    @Test
    public void TC02_verifyLatestBookingDateAtHistoryPage() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        // Step 1: Select Movie and Show Time at Home page
        ExtentReportManager.info("Step 1: Select Movie and Show Time at Home page");
        LOG.info("Step 1: Select Movie and Show Time at Home page");
        selectMovieAndShowTime();
        // Step 2: Select seat at Seat page
        ExtentReportManager.info("Step 2: Select seat at Seat page");
        LOG.info("Step 2: Select seat at Seat page");
        seatPage.selectRandomSeat();
        //Get values at all fields at the Ticket

        String expectNameMovie = seatPage.getMovieName();

        int expectTotalPrice = seatPage.getDisplayedPriceSeat();

        String expectCinemaBranch = seatPage.getNameCinemaBranch();

        String expectScreenID = seatPage.getScreenID();

        List<String> expectSeatID = seatPage.getListSelectedSeatOnTicket();

        // Step 3: Click on the Book ticket button
        ExtentReportManager.info("Step 3: Click on the Book ticket button");
        LOG.info("Step 3: Click on the Book ticket button");
        seatPage.clickBookTicketButton();
        // Step 4: Click on the Agree button
        ExtentReportManager.info("Step 4: Click on the Agree button");
        LOG.info("Step 4: Click on the Agree button");
        Reporter.log("Step 4: Click on the Agree button");
        seatPage.clickAgreeButtonInAlert();
        //Get the booking time when user click the Agree button
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        context.set("bookingTime", LocalDateTime.now().format(formatter));
        String expectOrderTime = context.get("bookingTime", String.class);
        // Step 5: Navigate to the History page
        ExtentReportManager.info("Step 5: Navigate to the History page");
        LOG.info("Step 5: Navigate to the History page");
        historyPage = homePage.navigateHistoryPage();
        //Step 6: Verify values of the lastest booking
        ExtentReportManager.info("Step 6: Verify values of the lastest booking");
        LOG.info("Step 6: Verify values of the lastest booking");
        //VP1: Verify the order time value
        ExtentReportManager.info("VP1: Verify the order time value");
        LOG.info("VP1: Verify the order time value");
        String actualOrderTime = historyPage.getLatestTicketDate();
        softAssert.assertEquals(actualOrderTime, expectOrderTime, "Ngày đặt vé mới nhất không đúng");
        //VP2: Verify the movie name
        ExtentReportManager.info("VP2: Verify the movie name");
        LOG.info("VP2: Verify the movie name");
        String actualNameMovie = historyPage.getLatestTicketMovieName();
        softAssert.assertEquals(actualNameMovie, expectNameMovie, "Tên phim không đúng");
        //VP3: Verify the total price
        ExtentReportManager.info("VP3: Verify the total price");
        LOG.info("VP3: Verify the total price");
        int actualTotalPrice = historyPage.getLatestTicketTotalPrice();
        softAssert.assertEquals(actualTotalPrice, expectTotalPrice, "Tổng giá vé không đúng");
        //VP3: Verify the cinema branch
        ExtentReportManager.info("VP3: Verify the cinema branch");
        LOG.info("VP3: Verify the cinema branch");
        String actualCinemaBranch = historyPage.getLatestTicketCinemaBranch();
        softAssert.assertEquals(actualCinemaBranch, expectCinemaBranch, "Cụm rạp không đúng");
        //VP4: Verify the screen ID
        ExtentReportManager.info("VP4: Verify the screen ID");
        LOG.info("VP4: Verify the screen ID");
        String actualScreenID = historyPage.getLatestTicketScreenID();
        softAssert.assertEquals(actualScreenID, expectScreenID, "Mã rạp không đúng");
        //VP5: Verify list selected seat
        ExtentReportManager.info("VP5: Verify list selected seat");
        LOG.info("VP5: Verify list selected seat");
        List<String> actualSeatID = historyPage.getLatestListSeat();
        softAssert.assertEquals(actualSeatID.toArray(), expectSeatID.toArray(), "Mã ghế không đúng");
        softAssert.assertAll();

    }

}
