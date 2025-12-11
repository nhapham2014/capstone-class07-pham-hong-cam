package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.SeatPage;
import reports.ExtentReportManager;


public class TC_MovieTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;

    @Test
    public void TC01_verifyMovieNameAtDetailPage() {
        homePage = new HomePage(driver);
        //Step 1: Select a movie thumbnail
        ExtentReportManager.info("Step 1: Select a movie thumbnail");
        LOG.info("Step 1: Select a movie thumbnail");
        String titleList = homePage.getMovieTitle("avatar-2_gp09");
        detailMoviePage = homePage.selectThumbnailMovie("avatar-2_gp09");
        // Step 2: Verify movie name at detail page
        ExtentReportManager.info("Step 2: Verify movie name at detail page");
        LOG.info("Step 2: Verify movie name at detail page");
        Assert.assertEquals(detailMoviePage.getMovieDetailTitle(), titleList,
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
    public void TC03_navigateToMovieDetailPageAfterClickBuyTicketButton() {
        homePage = new HomePage(driver);
        //Step 1: Click 'Mua Vé' button at a movie
        ExtentReportManager.info("Step 1: Click 'Mua Vé' button at a movie");
        LOG.info("Step 1: Click 'Mua Vé' button at a movie");
        detailMoviePage = homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        // Step 2: Verify navigate to movie detail page
        ExtentReportManager.info("Step 2: Verify navigate to movie detail page");
        LOG.info("Step 2: Verify navigate to movie detail page");
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"),
                "Không chuyển sang màn hình chi tiết phim sau khi bấm nút Mua Vé");

    }

    @Test
    public void TC04_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketByPosterFilm() {
        homePage = new HomePage(driver);
        //Step 1: Click on the Buy ticket at the Poster film
        ExtentReportManager.info("Step 1: Click on the Buy ticket at the Poster film");
        LOG.info("Step 1: Click on the Buy ticket at the Poster film");
        detailMoviePage = homePage.clickBuyTicketAtMovie("avatar-2_gp09");
        //Step 2: Select the show time at Detail movie page
        ExtentReportManager.info("Step 2: Select the show time at Detail movie page");
        LOG.info("Step 2: Select the show time at Detail movie page");
        seatPage = detailMoviePage.selectShowTime("cgv","CGV - VivoCity", "07-10-2021", "08:25");
        //Step 3: verify values on ticket at Seat page
        //VP1: Verify the movie name
        ExtentReportManager.info("VP1: Verify the movie name");
        LOG.info("VP1: Verify the movie name");
        Assert.assertEquals(seatPage.getMovieName(), "AVATAR 2", "Hiển thị không đúng tên phim");
        //VP2: Verify the cinema branch
        ExtentReportManager.info("VP2: Verify the cinema branch");
        LOG.info("VP2: Verify the cinema branch");
        Assert.assertEquals(seatPage.getNameCinemaBranch(), "CGV - VivoCity", "Không hiển thị đúng tên cụm rạp");
        //VP3: Verify the address cinema
        ExtentReportManager.info("VP3: Verify the address cinema");
        LOG.info("VP3: Verify the address cinema");
        Assert.assertEquals(seatPage.getAddressCinema(), "Lầu 5, Trung tâm thương mại SC VivoCity - 1058 Nguyễn Văn Linh, Q. 7", "Không hiển thị đúng địa chỉ cụm rạp");
        //VP4: Verify the date of show time
        ExtentReportManager.info("VP4: Verify the date of show time");
        LOG.info("VP4: Verify the date of show time");
        Assert.assertEquals(seatPage.getDateOfShowTime(), "07-10-2021", "Không hiển thị đúng ngày chiếu");
        //VP5: Verify the time of show time
        ExtentReportManager.info("VP5: Verify the time of show time");
        LOG.info("VP5: Verify the time of show time");
        Assert.assertEquals(seatPage.getTimeOfShowTime(), "08:25", "Không hiển thị đúng giờ chiếu");
    }


}
