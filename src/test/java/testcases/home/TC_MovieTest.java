package testcases.home;

import base.BaseTestWithLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DetailMoviePage;
import pages.HomePage;
import pages.SeatPage;
import reports.ExtentReportManager;


public class TC_MovieTest extends BaseTestWithLogin {
    HomePage homePage;
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;
    final String movieID = "avatar-2_gp09";
    final String movieName = "AVATAR 2";
    final String cinemaBrand = "cgv";
    final String cinemaBranch = "CGV - VivoCity";
    final String showDate = "07-10-2021";
    final String showTime = "08:25";
    final String cinemaAddress = "Lầu 5, Trung tâm thương mại SC VivoCity - 1058 Nguyễn Văn Linh, Q. 7";


    @Test
    public void TC01_verifyMovieNameAtDetailPage() {
        homePage = new HomePage(driver);
        //Step 1: Select a movie thumbnail
        ExtentReportManager.info("Step 1: Select a movie thumbnail");
        LOG.info("Step 1: Select a movie thumbnail");
        String titleList = homePage.getMovieTitle(movieID);
        detailMoviePage = homePage.selectThumbnailMovie(movieID);
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
        homePage.clickTrailerMovie(movieID);
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
       homePage.clickBuyTicketAtMovie(movieID);
        // Step 2: Verify navigate to movie detail page
        ExtentReportManager.info("Step 2: Verify navigate to movie detail page");
        LOG.info("Step 2: Verify navigate to movie detail page");
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"),
                "Không chuyển sang màn hình chi tiết phim sau khi bấm nút Mua Vé");

    }

    @Test
    public void TC04_verifyInformationOnTicketAtSeatPageWhenUserBuyTicketByPosterFilm() {
        SoftAssert softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //Step 1: Click on the Buy ticket at the Poster film
        ExtentReportManager.info("Step 1: Click on the Buy ticket at the Poster film");
        LOG.info("Step 1: Click on the Buy ticket at the Poster film");
        detailMoviePage = homePage.clickBuyTicketAtMovie(movieID);
        //Step 2: Select the show time at Detail movie page
        ExtentReportManager.info("Step 2: Select the show time at Detail movie page");
        LOG.info("Step 2: Select the show time at Detail movie page");
        seatPage = detailMoviePage.selectShowTime(cinemaBrand,cinemaBranch, showDate, showTime);
        //Step 3: verify values on ticket at Seat page
        //VP1: Verify the movie name
        ExtentReportManager.info("VP1: Verify the movie name");
        LOG.info("VP1: Verify the movie name");
        softAssert.assertEquals(seatPage.getMovieName(), movieName, "Hiển thị không đúng tên phim");
        //VP2: Verify the cinema branch
        ExtentReportManager.info("VP2: Verify the cinema branch");
        LOG.info("VP2: Verify the cinema branch");
        softAssert.assertEquals(seatPage.getNameCinemaBranch(), cinemaBranch, "Không hiển thị đúng tên cụm rạp");
        //VP3: Verify the address cinema
        ExtentReportManager.info("VP3: Verify the address cinema");
        LOG.info("VP3: Verify the address cinema");
        softAssert.assertEquals(seatPage.getAddressCinema(), cinemaAddress, "Không hiển thị đúng địa chỉ cụm rạp");
        //VP4: Verify the date of show time
        ExtentReportManager.info("VP4: Verify the date of show time");
        LOG.info("VP4: Verify the date of show time");
       softAssert.assertEquals(seatPage.getDateOfShowTime(), showDate, "Không hiển thị đúng ngày chiếu");
        //VP5: Verify the time of show time
        ExtentReportManager.info("VP5: Verify the time of show time");
        LOG.info("VP5: Verify the time of show time");
        softAssert.assertEquals(seatPage.getTimeOfShowTime(), showTime, "Không hiển thị đúng giờ chiếu");
        softAssert.assertAll();
    }


}
