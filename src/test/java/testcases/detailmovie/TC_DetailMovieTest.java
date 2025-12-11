package testcases.detailmovie;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.SeatPage;
import reports.ExtentReportManager;

public class TC_DetailMovieTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;
    @Test
    public void TC01_verifyAllShowtimesAreAfterCurrentTime() {
        homePage = new HomePage(driver);
        detailMoviePage = new DetailMoviePage(driver);
        // Step 1: Click 'Mua Vé' button at a movie
        ExtentReportManager.info("Step 1: Click 'Mua Vé' button at a movie ");
        LOG.info("Step 1: Click 'Mua Vé' button at a movie ");
        homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        //Step 2: Select a cinema logo
        ExtentReportManager.info("Step 2: Select a cinema logo");
        LOG.info("Step 2: Select a cinema logo");
        detailMoviePage.clickCinemaLogo("BHD");
        // Step 3: Verify all showtimes are after current time
        ExtentReportManager.info("Step 3: Verify all showtimes are after current time");
        LOG.info("Step 3: Verify all showtimes are after current time");
        Assert.assertTrue(detailMoviePage.isAllShowtimesInFuture(),
                    "Ngày giờ suất chiếu phải lớn hơn hiện tại");

    }
    @Test
    public void TC02_navigateToSeatPageAfterSelectShowTime(){
        homePage = new HomePage(driver);
        // Step 1: Click 'Mua Vé' button at a movie
        ExtentReportManager.info("Step 1: Click 'Mua Vé' button at a movie");
        LOG.info("Step 1: Click 'Mua Vé' button at a movie");
        detailMoviePage = homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        // Step 2: Select a show time
        ExtentReportManager.info("Step 2: Select a show time");
        LOG.info("Step 2: Select a show time");
        seatPage = detailMoviePage.selectShowTime("cgv","CGV - VivoCity","07-10-2021","08:25");
        // Step 3: Verify navigate to seat page
        ExtentReportManager.info("Step 3: Verify navigate to seat page");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demo1.cybersoft.edu.vn/purchase"));

    }
    @Test
    public void TC03_verifyListCinemaWhenSelectCinemaLogoAtDetailMoviePage(){
        homePage = new HomePage(driver);
        // Step 1: Click 'Mua Vé' button at a movie
        ExtentReportManager.info("Step 1: Click 'Mua Vé' button at a movie");
        LOG.info("Step 1: Click 'Mua Vé' button at a movie");
        detailMoviePage = homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        //Step 2: Select a logo of cinema
        ExtentReportManager.info("Step 2: Select a logo of cinema");
        LOG.info("Step 2: Select a logo of cinema");
        detailMoviePage.clickCinemaLogo("cgv");
        //Step 3: Verify list cinema
        ExtentReportManager.info("Step 3: Verify list cinema ");
        LOG.info("Step 3: Verify list cinema ");
        Assert.assertTrue(detailMoviePage.isCinemaBelongToSystem("CGV"),
                "Có rạp KHÔNG thuộc hệ thống!");

    }
}
