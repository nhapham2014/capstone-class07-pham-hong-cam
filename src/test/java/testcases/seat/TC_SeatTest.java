package testcases.seat;
import base.BaseTestWithLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DetailMoviePage;
import pages.HomePage;

import pages.SeatPage;
import reports.ExtentReportManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TC_SeatTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;
    private void selectMovieAndShowTime(){
        HomePage homePage = new HomePage(driver);
        homePage.selectMovie("gái già lắm chiêu");
        homePage.selectCinema("CGV - Vincom Gò Vấp");
        homePage.selectDate("21/12/2021 ~ 16:00");
        seatPage = homePage.clickBuyTicket();

    }
    @Test
    public void TC01_verifyColorOfSeatAfterSelected() {
        selectMovieAndShowTime();
        //Step 1:
        seatPage.selectSeat("90");
        String color = seatPage.getSeatColor("90");
        Assert.assertTrue(color.contains("green"),
                "Ghế không đổi màu xanh sau khi chọn");
    }
    @Test
    public void TC02_verifyColorOfSeatAfterUnselectSeat() {
        selectMovieAndShowTime();
        //Step 1:
        seatPage.selectSeat("90");
        //Step 2:
        seatPage.selectSeat("90");

        String color = seatPage.getSeatColor("90");
        Assert.assertTrue(color.isEmpty(),
                "Ghế vẫn màu xanh dù đã bỏ chọn");
    }
    @Test
    public void TC03_verifySelectedSeatShowsOnTicket(){
        selectMovieAndShowTime();
        seatPage.selectSeat("90");
        seatPage.selectSeat("91");
        seatPage.selectSeat("93");
       Assert.assertTrue(seatPage.isDisplayCorrectSeatOnTicket(),"Hiển thị ghế không đúng trên vé");
    }
    @Test
    public void TC05_verifyDisplayCorrectPriceAtInformationOfTicket(){
        selectMovieAndShowTime();
        //User don't select any seat
        //Assert.assertEquals(seatPage.getDisplayedPriceSeat(),0,"Giá hiển thị không đúng");
        //User select 1 VIP seat
        seatPage.selectSeat("90");
       // Assert.assertEquals(seatPage.getDisplayedPriceSeat(),seatPage.totalPrice(),"Gía hiển thị không đúng");
        //User select 1 VIP seat and 1 Regular seat
        seatPage.selectSeat("02");
        Assert.assertEquals(seatPage.getDisplayedPriceSeat(),seatPage.totalPrice(),"Gía hiển thị không đúng");
        System.out.println(seatPage.getDisplayedPriceSeat());
        System.out.println(seatPage.totalPrice());
    }
    @Test
    public void TC_verifySeatClearedWhenBrowserBack(){
        selectMovieAndShowTime();
        seatPage.selectSeat("90");
        seatPage.selectSeat("91");
        seatPage.selectSeat("93");
        seatPage.browserBack();
        selectMovieAndShowTime();
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        System.out.println(numberOfSelectedSeat);
        Assert.assertEquals(numberOfSelectedSeat,0,"Ghế không được bỏ chọn khi quay lại trang trước đó");
    }
    @Test
    public void TC_verifySeatClearedWhenReloadPage(){
        selectMovieAndShowTime();
        seatPage.selectSeat("90");
        seatPage.reloadPage();
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        System.out.println(numberOfSelectedSeat);
        Assert.assertEquals(numberOfSelectedSeat,0, "Ghế không được bỏ chọn khi quay lại trang trước đó");
    }

    @Test
    public void TC_verifyErrorMessageWhenBookTicketWithoutSelectingSeat(){
        selectMovieAndShowTime();
        seatPage.clickBookTicketButton();
        String errorMessage = seatPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Bạn chưa chọn ghế","Thông báo lỗi không đúng khi đặt vé mà không chọn ghế");
    }
    @Test
    public void TC_verifySuccessMessageWhenBookTicketWithSelectingSeat(){
        selectMovieAndShowTime();
        seatPage.selectSeat("91");
        seatPage.clickBookTicketButton();
        String successMessage = seatPage.getSuccessMessage();
        Assert.assertEquals(successMessage,"Đặt vé thành công","Thông báo đặt vé không đúng khi đặt vé với ghế đã chọn");
    }
    @Test
    public void TC_navigateToPaymentPageAfterBookTicketWithSelectingSeat(){
        selectMovieAndShowTime();
        seatPage.selectSeat("77");
        seatPage.clickBookTicketButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn//"),"Không điều hướng đến trang chủ sau khi đặt vé với ghế đã chọn");
    }
    @Test
    public void TC_verifyDataAtSeatPageAfterSelectSeatSuccessfully(){
        selectMovieAndShowTime();
        seatPage.selectSeat("60");
        seatPage.clickBookTicketButton();
        seatPage.clickAgreeButtonInAlert();
        Assert.assertTrue(seatPage.isDisableSeat("60"),"Ghế đã chọn không bị vô hiệu hóa sau khi đặt vé thành công");
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        System.out.println(numberOfSelectedSeat);
        Assert.assertEquals(numberOfSelectedSeat,0, "Ghế không được reset sau khi đặt vé thành công");

    }


}
