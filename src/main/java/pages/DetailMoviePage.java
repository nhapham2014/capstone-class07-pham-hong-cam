package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailMoviePage extends CommonPage {
    SeatPage seatPage;
    private By byLbMovieName = By.xpath("//h1[contains(@class,'MuiTypography-h1')]");
    private By byListShowTime = By.xpath("//div[contains(@class,'MuiGrid-item')]//a[p[contains(text(),'-')]]");
    private By byDateText = By.xpath(".//p[contains(text(),'-')]");
    private By byTimeText = By.xpath(".//p[contains(text(),':')]");
    private By byListCinemaBranch = By.xpath("//div[@role='tabpanel']//div[contains(@class,'MuiBox')]/div[contains(@class, 'container')]");

    public DetailMoviePage(WebDriver driver) {
        super(driver);
    }

    public String getMovieDetailTitle() {
        waitUtil.waitForVisibilityOfElementLocated(byLbMovieName);

        return getText(byLbMovieName);
    }

    public boolean isAllShowtimesInFuture() {
        waitUtil.waitForVisibilityOfElementLocated(byListShowTime);
        List<WebElement> links = driver.findElements(byListShowTime);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        // Date format trong UI
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (WebElement link : links) {
            // Ghép thành chuỗi datetime
            String dateTimeText = getText(byDateText) + " " + getText(byTimeText);
            // Parse thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, formatter);
            dateTimes.add(dateTime);
        }
        // Kiểm tra từng datetime
        for (LocalDateTime time : dateTimes) {
            if (!time.isAfter(now)) {
                return false;
            }
        }
        return true;
    }
    public void clickCinemaLogo(String cinemaLogo) {
        By byCinemaLogo = By.xpath("//img[contains(@alt, '" + cinemaLogo + "')]");
        click(byCinemaLogo);
    }

    public SeatPage selectShowTime(String cinemaLogo, String cinemaBranch, String date, String time) {
        clickCinemaLogo(cinemaLogo);
        By byShowTimeOption = By.xpath("//div[div[h3[contains(text(),'" + cinemaBranch + "')]]]//a[p[normalize-space()='" + date + "'] and p[normalize-space()='" + time + "']]");
        click(byShowTimeOption);
        waitUtil.waitForPageLoaded();
        seatPage = new SeatPage(driver);
        return seatPage;

    }



    public boolean isCinemaBelongToSystem(String brandCinema) {
        List<WebElement> elements = driver.findElements(byListCinemaBranch);
        List<String> listCinema = new ArrayList<>();
        // return elements.stream().map(WebElement::getText).collect(Collectors.toList());
        for (WebElement e : elements) {
            listCinema.add(e.getText());
        }
        for (String c : listCinema) {
            if (!c.contains(brandCinema)) {
                return false;
            }
        }
        return true;
    }
}
