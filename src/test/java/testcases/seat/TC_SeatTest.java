package testcases.seat;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SeatPage;
import reports.ExtentReportManager;

public class TC_SeatTest extends BaseTestWithLogin {
    SeatPage seatPage;

    private void selectMovieAndShowTime() {
        HomePage homePage = new HomePage(driver);
        seatPage = homePage.buyTicketByFilter("Captain Marvel 2",
                "CGV - Golden Plaza",
                "26/05/2022 ~ 18:30");
    }

    @Test
    public void TC01_verifyColorOfSeatAfterSelected() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: Select the seat
        ExtentReportManager.info("Step 2: Select the seat");
        LOG.info("Step 2: Select the seat");
        seatPage.selectRandomSeat();
        String numberSeat = seatPage.selectRandomSeat();
        //Step 3: verify color of the selected seat
        ExtentReportManager.info("Step 3: verify color of the selected seat");
        LOG.info("Step 3: verify color of the selected seat");
        String color = seatPage.getSeatColor(numberSeat);
        ExtentReportManager.verifyEqualsString(color, "green",
                "Ghế không đổi màu xanh sau khi chọn",driver);
        Assert.assertEquals(color, "green",
                "Ghế không đổi màu xanh sau khi chọn");
    }

    @Test
    public void TC02_verifyColorOfSeatAfterUnselectSeat() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeat();
        String numberSeat = seatPage.selectRandomSeat();
        //Step 3: unselect the seat
        ExtentReportManager.info("Step 3: unselect the seat");
        LOG.info("Step 3: unselect the seat");
        seatPage.selectSeat(numberSeat);
        //Step 4: verify color of the unselect seat
        ExtentReportManager.info("Step 4: verify color of the unselect seat");
        LOG.info("Step 4: verify color of the unselect seat");
        String color = seatPage.getSeatColor(numberSeat);
        ExtentReportManager.verifyTrue(color.isEmpty(),
                "Ghế vẫn màu xanh dù đã bỏ chọn",driver);
        Assert.assertTrue(color.isEmpty(),
                "Ghế vẫn màu xanh dù đã bỏ chọn");
    }

    @Test
    public void TC03_verifySelectedSeatShowsOnTicket() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeats(3);

        //Step 3: verify the seat on ticket
        ExtentReportManager.info("Step 3: verify the seat on ticket");
        LOG.info("Step 3: verify the seat on ticket");
        Assert.assertTrue(seatPage.isDisplayCorrectSeatOnTicket(), "Hiển thị ghế không đúng trên vé");
    }

    @Test
    public void TC05_verifyDisplayCorrectPriceAtInformationOfTicket() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeats(2);
        //Step 3: verify the total price
        ExtentReportManager.info("Step 3: verify the total price");
        LOG.info("Step 3: verify the total price");
        ExtentReportManager.verifyEqualsNumber(seatPage.getDisplayedPriceSeat(), seatPage.totalPrice(), "Gía hiển thị không đúng",driver);
        Assert.assertEquals(seatPage.getDisplayedPriceSeat(), seatPage.totalPrice(), "Gía hiển thị không đúng");
    }

    @Test
    public void TC_verifySeatClearedWhenBrowserBack() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeats(3);
        //Step 3: Back to the previous page
        ExtentReportManager.info("Step 3: Back to the previous page");
        LOG.info("Step 3: Back to the previous page");
        seatPage.browserBack();
        //Step 4: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 4: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 4: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 5: verify seat on ticket be is reset on ticket
        ExtentReportManager.info("Step 5: verify seat on ticket be is reset on ticket");
        LOG.info("Step 5: verify seat on ticket be is reset on ticket");
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        ExtentReportManager.verifyEqualsNumber(numberOfSelectedSeat, 0, "Ghế không được bỏ chọn khi quay lại trang trước đó",driver);
        Assert.assertEquals(numberOfSelectedSeat, 0, "Ghế không được bỏ chọn khi quay lại trang trước đó");
    }

    @Test
    public void TC_verifySeatClearedWhenReloadPage() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeat();
        //Step 3: Reload/F5 page
        ExtentReportManager.info("Step 3: Reload/F5 page");
        LOG.info("Step 3: Reload/F5 page");
        seatPage.reloadPage();
        //Step 4: verify seat on ticket be is reset on ticket
        ExtentReportManager.info("Step 4: verify seat on ticket be is reset on ticket");
        LOG.info("Step 4: verify seat on ticket be is reset on ticket");
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        ExtentReportManager.verifyEqualsNumber(numberOfSelectedSeat, 0, "Ghế không được bỏ chọn khi quay lại trang trước đó",driver);
        Assert.assertEquals(numberOfSelectedSeat, 0, "Ghế không được bỏ chọn khi quay lại trang trước đó");
    }

    @Test
    public void TC_verifyErrorMessageWhenBookTicketWithoutSelectingSeat() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: Click on the book ticket button
        ExtentReportManager.info("Step 2: Click on the book ticket button");
        LOG.info("Step 2: Click on the book ticket button");
        seatPage.clickBookTicketButton();
        //Step 3: Verify the error message when user doesn't select seat yet
        ExtentReportManager.info("Step 3: Verify the error message when user doesn't select seat yet");
        LOG.info("Step 3: Verify the error message when user doesn't select seat yet");
        String errorMessage = seatPage.getErrorMessage();
        ExtentReportManager.verifyEqualsString(errorMessage, "Bạn chưa chọn ghế", "Thông báo lỗi không đúng khi đặt vé mà không chọn ghế",driver);
        Assert.assertEquals(errorMessage, "Bạn chưa chọn ghế", "Thông báo lỗi không đúng khi đặt vé mà không chọn ghế");
    }

    @Test
    public void TC_verifySuccessMessageWhenBookTicketWithSelectingSeat() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeat();
        //Step 3: Click on the book ticket button
        ExtentReportManager.info("Step 3: Click on the book ticket button");
        LOG.info("Step 3: Click on the book ticket button");
        seatPage.clickBookTicketButton();
        //Step 4: Verify the success message
        ExtentReportManager.info("Step 4: Verify the success message");
        LOG.info("Step 4: Verify the success message");
        String successMessage = seatPage.getSuccessMessage();
        ExtentReportManager.verifyEqualsString(successMessage, "Đặt vé thành công", "Thông báo đặt vé không đúng khi đặt vé với ghế đã chọn",driver);
        Assert.assertEquals(successMessage, "Đặt vé thành công", "Thông báo đặt vé không đúng khi đặt vé với ghế đã chọn");
    }

    @Test
    public void TC_navigateToPaymentPageAfterBookTicketWithSelectingSeat() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeat();
        //Step 3: Click on the Book Ticket button
        ExtentReportManager.info("Step 3: Click on the Book Ticket button");
        LOG.info("Step 3: Click on the Book Ticket button");
        seatPage.clickBookTicketButton();
        //Step 4: Verify the system navigate to Home page
        ExtentReportManager.info("Step 4: Verify the system navigate to Home page");
        LOG.info("Step 4: Verify the system navigate to Home page");
        ExtentReportManager.verifyTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn//"), "Không điều hướng đến trang chủ sau khi đặt vé với ghế đã chọn",driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn//"), "Không điều hướng đến trang chủ sau khi đặt vé với ghế đã chọn");
    }

    @Test
    public void TC_verifyDataAtSeatPageAfterSelectSeatSuccessfully() {
        //Step 1: Select movie name, cinema branch, show time at the filter section
        ExtentReportManager.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        LOG.info("Step 1: Select movie name, cinema branch, show time at the filter section");
        selectMovieAndShowTime();
        //Step 2: select the seat
        ExtentReportManager.info("Step 2: select the seat");
        LOG.info("Step 2: select the seat");
        seatPage.selectRandomSeat();
        String seatNumber = seatPage.selectRandomSeat();
        //Step 3: Click on the Book Ticket button
        ExtentReportManager.info("Step 3: Click on the Book Ticket button");
        LOG.info("Step 3: Click on the Book Ticket button");
        seatPage.clickBookTicketButton();
        seatPage.clickAgreeButtonInAlert();
        //Step 4: Verify status of the selected seat
        ExtentReportManager.info("Step 4: Verify status of the selected seat");
        LOG.info("Step 4: Verify status of the selected seate");
        Assert.assertTrue(seatPage.isDisableSeat(seatNumber), "Ghế đã chọn không bị vô hiệu hóa sau khi đặt vé thành công");
        int numberOfSelectedSeat = seatPage.getListSelectedSeatOnTicket().size();
        ExtentReportManager.verifyEqualsNumber(numberOfSelectedSeat, 0, "Ghế không được reset sau khi đặt vé thành công",driver);
        Assert.assertEquals(numberOfSelectedSeat, 0, "Ghế không được reset sau khi đặt vé thành công");
    }


}
