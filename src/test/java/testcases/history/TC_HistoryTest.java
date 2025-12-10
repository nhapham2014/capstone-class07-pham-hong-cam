package testcases.history;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.*;
import reports.ExtentReportManager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TC_HistoryTest extends BaseTestWithLogin {
    HomePage homePage;
    LoginPage loginPage;
    DetailMoviePage detailMoviePage;
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
        historyPage = homePage.navigateHistoryPage();
        int numberBooking = historyPage.getNumberOfTicketsInHistory();
        Assert.assertEquals(numberBooking, 32, "Hiển thị sai số lượng booking");
    }

    @Test
    public void TC() {
        homePage = new HomePage(driver);
        historyPage = homePage.navigateHistoryPage();
        System.out.println(historyPage.getLatestTicketDate());
    }

    @Test
    public void TC02_verifyLatestBookingDateAtHistoryPage() {
        homePage = new HomePage(driver);
        selectMovieAndShowTime();
        seatPage.selectSeat("26");
        //seatPage.selectSeat("30");
        String expectNameMovie = seatPage.getMovieName();
        System.out.println(expectNameMovie);
        int expectTotalPrice = seatPage.getDisplayedPriceSeat();
        System.out.println(expectTotalPrice);
        String expectCinemaBranch = seatPage.getNameCinemaBranch();
        System.out.println(expectCinemaBranch);
        String expectScreenID = seatPage.getScreenID();
        System.out.println(expectScreenID);
        List<String> expectSeatID = seatPage.getListSelectedSeatOnTicket();
        System.out.println(expectSeatID);
        seatPage.clickBookTicketButton();
        seatPage.clickAgreeButtonInAlert();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        context.set("bookingTime", LocalDateTime.now().format(formatter));
        String expectOrderTime = context.get("bookingTime", String.class);
        System.out.println(expectOrderTime);
        historyPage = homePage.navigateHistoryPage();
        String actualOrderTime = historyPage.getLatestTicketDate();
        System.out.println(actualOrderTime);
        String actualNameMovie = historyPage.getLatestTicketMovieName();
        System.out.println(actualNameMovie);
        int actualTotalPrice = historyPage.getLatestTicketTotalPrice();
        System.out.println(actualTotalPrice);
        String actualCinemaBranch = historyPage.getLatestTicketCinemaBranch();
        System.out.println(actualCinemaBranch);
        String actualScreenID = historyPage.getLatestTicketScreenID();
        System.out.println(actualScreenID);
        List<String> actualSeatID = historyPage.getLatestListSeat();
        System.out.println(actualSeatID);

        Assert.assertEquals(actualOrderTime, expectOrderTime, "Ngày đặt vé mới nhất không đúng");
        Assert.assertEquals(actualNameMovie, expectNameMovie, "Tên phim không đúng");
        Assert.assertEquals(actualTotalPrice, expectTotalPrice, "Tổng giá vé không đúng");
        Assert.assertEquals(actualCinemaBranch, expectCinemaBranch, "Cụm rạp không đúng");
        Assert.assertEquals(actualScreenID, expectScreenID, "Mã rạp không đúng");
        Assert.assertEquals(actualSeatID.toArray(), expectSeatID.toArray(), "Mã ghế không đúng");
    }

}
