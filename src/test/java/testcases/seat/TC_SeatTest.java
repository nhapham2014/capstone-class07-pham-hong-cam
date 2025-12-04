package testcases.seat;
import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TC_SeatTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;
    private void selectMovieAndShowTime(){
        HomePage homePage = new HomePage(driver);
        homePage.bookTicketByFilter("AVATAR 2", "CGV - Golden Plaza", "15/10/2021 ~ 08:42");

    }
    @Test
    public void TC01_verifyColorOfSeatAfterSelected() {
        homePage = new HomePage(driver);
        seatPage = new SeatPage(driver);
        selectMovieAndShowTime();
        //Step 1:
        seatPage.selectSeat(90);
        String color = seatPage.getSeatColor(90);
        Assert.assertTrue(color.contains("green"),
                "Ghế không đổi màu xanh sau khi chọn");
    }
    @Test
    public void TC02_verifyColorOfSeatAfterUnselectSeat() {
        homePage = new HomePage(driver);
        seatPage = new SeatPage(driver);
        selectMovieAndShowTime();
        //Step 1:
        seatPage.selectSeat(90);
        //Step 2:
        seatPage.selectSeat(90);
        String color = seatPage.getSeatColor(90);
        Assert.assertTrue(color.isEmpty(),
                "Ghế vẫn màu xanh dù đã bỏ chọn");
    }
    @Test
    public void TC03_verifySeatIDAtInformationTicket(){
        homePage = new HomePage(driver);
        seatPage = new SeatPage(driver);
        selectMovieAndShowTime();
        seatPage.selectSeat(90);
        String seatnumber = seatPage.getSeatID();
        System.out.println(seatnumber);
        Assert.assertEquals(seatnumber, "Ghế 90","Không hiển thị đúng ghế");
    }
    @Test
    public void TC04_verifyCountSelectedSeatAtInformationTicket(){
        homePage= new HomePage(driver);
        seatPage = new SeatPage(driver);
        selectMovieAndShowTime();
        seatPage.selectSeat(90);
        seatPage.selectSeat(91);
        Assert.assertTrue(seatPage.isDisplayCorrectOfNumberSeat(),"Hiển thị số luượng ghế không đúng tại thông tin vé");

    }
}
