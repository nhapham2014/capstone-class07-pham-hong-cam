package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.LoginPage;
import pages.SeatPage;
import reports.ExtentReportManager;

import java.time.LocalDateTime;
import java.util.List;

public class TC_MovieTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;

    @Test
    public void TC01_verifyMovieNameAtDetailPage() {
        homePage = new HomePage(driver);
        detailMoviePage = new DetailMoviePage(driver);
        //Step 1: Select a movie thumbnail
        ExtentReportManager.info("Step 1: Select a movie thumbnail");
        LOG.info("Step 1: Select a movie thumbnail");
        homePage.selectThumbnailMovie("avatar-2_gp09");
        // Step 2: Verify movie name at detail page
        ExtentReportManager.info("Step 2: Verify movie name at detail page");
        LOG.info("Step 2: Verify movie name at detail page");
        String titleList = homePage.getMovieTitle("avatar-2_gp09");
        String titleDetail = detailMoviePage.getMovieDetailTitle();
        System.out.println("Tên phim trang detail: " + titleDetail);
        Assert.assertEquals(titleDetail, titleList,
                "Tên phim KHÔNG trùng nhau!");
    }

    @Test
    public void TC02_verifyTrailerPopupDisplay() {
        homePage = new HomePage(driver);
        //Step 1: Click trailer video icon
        ExtentReportManager.info("Step 1: Click trailer video icon");
        LOG.info("Step 1: Click trailer video icon");
        homePage.clickTrailerMovie("avatar-2_gp09");
        //Step 2: Verify trailer popup display
        ExtentReportManager.info("Step 2: Verify trailer popup display");
        LOG.info("Step 2: Verify trailer popup display");
        Assert.assertTrue(
                homePage.isTrailerDisplayed(),
                "Trailer KHÔNG hiển thị sau khi click Video!"
        );
    }
    @Test
    public void TC03_navigateToMovieDetailPageAfterClickBuyTicketButton(){
        homePage = new HomePage(driver);
        //Step 1: Click 'Mua Vé' button at a movie
        ExtentReportManager.info("Step 1: Click 'Mua Vé' button at a movie");
        LOG.info("Step 1: Click 'Mua Vé' button at a movie");
        homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        // Step 2: Verify navigate to movie detail page
        ExtentReportManager.info("Step 2: Verify navigate to movie detail page");
        LOG.info("Step 2: Verify navigate to movie detail page");
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"),
                "Không chuyển sang màn hình chi tiết phim sau khi bấm nút Mua Vé");

    }
    @Test
    public void TC04_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketByPosterFilm(){
        homePage = new HomePage(driver);
        seatPage = new SeatPage(driver);
        homePage.buyTicketAtListMovie("avatar-2_gp09","cgv","CGV - VivoCity","07-10-2021","08:25");
        Assert.assertEquals(seatPage.getMovieName(),"AVATAR 2", "Hiển thị không đúng tên phim");
        Assert.assertEquals(seatPage.getNameCinemaBranch(),"CGV - VivoCity", "Không hiển thị đúng tên cụm rạp");
        Assert.assertEquals(seatPage.getAddressCinema(), "Lầu 5, Trung tâm thương mại SC VivoCity - 1058 Nguyễn Văn Linh, Q. 7", "Không hiển thị đúng địa chỉ cụm rạp");
        Assert.assertEquals(seatPage.getDateOfShowTime(),"07-10-2021", "Không hiển thị đúng ngày chiếu");
        Assert.assertEquals(seatPage.getTimeOfShowTime(),"08:25", "Không hiển thị đúng giờ chiếu");
    }


}
