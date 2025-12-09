package testcases.history;
import base.BaseTest;
import base.BaseTestWithLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.*;

import reports.ExtentReportManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TC_HistoryTest extends BaseTestWithLogin {
    HomePage homePage;
    LoginPage loginPage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;
    HistoryPage historyPage;
    @Test
    public void TC01_verifyNumberBookingAtHistoryPage(){
        homePage = new HomePage(driver);
        historyPage = homePage.navigateHistoryPage();
        int numberBooking = historyPage.getNumberOfTicketsInHistory();
        context.set("bookingTime", LocalDateTime.now());
        String expectedTime = context.get("bookingTime", String.class);
        Assert.assertEquals(numberBooking,32, "Hiển thị sai số lượng booking");
    }

}
