package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.text.Normalizer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends CommonPage {
    DetailMoviePage detailMoviePage;
    SeatPage seatPage;

    private By byLblUserProfile = By.xpath("//a[@href='/account']/h3");
    private By byDrdnFilm = By.xpath("//select[@name='film']");
    private By byDrndnCinema = By.xpath("//select[@name='cinema']");
    private By byDrdnShowtime = By.xpath("//select[@name='date']");
    private By byBtnBuyTicket = By.xpath("//button[.//span[text()='MUA VÉ NGAY']]");
    private By byFilmOptions = By.xpath("//select[@name='film']/option");
    private By byCinemaOptions = By.xpath("//select[@name='cinema']/option");
    private By byDateOptions = By.xpath("//select[@name='date']/option");
    private By byFrameVideo = By.xpath("//iframe[contains(@src,'youtube') or contains(@src,'video')]");
    private By byListCinemaNames = By.xpath("//div[@role='tabpanel']//div[@role='tablist']//h4");
    private By byLbSelectedCinemaBranch = By.xpath("//div[@role='tabpanel']//button[contains(@aria-selected,'true')]//h4");
    private By byLbNameMovie = By.xpath("//div[contains(@class,'MuiBox')]//h2");
    private By byLbDateShowTime = By.xpath("//div[contains(@class,'MuiBox')]//p[contains(text(),'-')]");
    private By byLbTimeShowTime = By.xpath("//div[contains(@class,'MuiBox')]//h3[contains(text(),':')]");
    private By byListShowTimeByCinema = By.xpath("//div[contains(@class,'MuiBox')]//a[div[p[contains(text(),'-')]]]");
    private By byDateText = By.xpath(".//p[contains(text(),'-')]");
    private By byTimeText = By.xpath(".//h3");
    private By byMsgPopupFilter = By.xpath("//*[@id='swal2-content']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getUserProfileName() {
        return getText(byLblUserProfile);
    }
    public void selectMovie(String movieName){
        // Wait for the option
        waitOptionsDropDownLoaded(byFilmOptions);
        selectOptionByText(byDrdnFilm,movieName);
        waitOptionsDropDownLoaded(byCinemaOptions);
    }
    public void selectCinema(String cinemaOption){
        selectOptionByText(byDrndnCinema,cinemaOption);
        waitOptionsDropDownLoaded(byDateOptions);
    }
    public void selectDate(String dateOption){
        // Wait for the option
        selectOptionByText(byDrdnShowtime,dateOption);
    }
    public int getDefaultValueCinemaOptionCount() {
         waitValueDefaultOnly(byCinemaOptions);
        return getOptionsCount(byCinemaOptions);
    }

    public int getCinemaOptionsAfterSelectMovie() {

        waitOptionsDropDownLoaded(byCinemaOptions);
        return getOptionsCount(byCinemaOptions);
    }
    public int getDefaultValueShowtimeOptionsCount() {
        waitValueDefaultOnly(byDateOptions);
        return getOptionsCount(byDateOptions);
    }
    public int getShowTimeOptionsAfterSelectMovie() {

        waitOptionsDropDownLoaded(byDateOptions);
        return getOptionsCount(byDateOptions);
    }
    public String getSelectedCinema() {
        return getSelectedOptionText(byDrndnCinema);

    }
    public String getWarningPopupMessage() {

        return getText(byMsgPopupFilter);
    }

    public String getSelectedShowtime() {
        return getSelectedOptionText(byDrdnShowtime);
    }

    public boolean cinemaIsReset() {
        return getSelectedCinema().equals("Rạp");
    }

    public boolean showtimeIsReset() {
        return getSelectedShowtime().equals("Ngày giờ chiếu");
    }
    public void clickBuyTicket() {
        click(byBtnBuyTicket);
    }
    public String getMovieTitle(String imageMovie) {
        By byLbMovieTitle = By.xpath("//div[contains(@style,'" + imageMovie +"')]/following-sibling::div//div[span='C18']");
        return getText(byLbMovieTitle).replace("C18", "").trim();
    }
    public void selectThumbnailMovie(String imageMovie){
        By byThumbnailMovie = By.xpath("//div[contains(@style, '" + imageMovie +"')]");
        click(byThumbnailMovie);
    }
    public void clickTrailerMovie(String imageMovie){
        By byImageMovie = By.xpath("//div[contains(@style, '" + imageMovie +"')]");
        By byBtnTrailerMovie = By.xpath("//div[contains(@style, '" + imageMovie +"')]//button");
        hover(byImageMovie);
        click(byBtnTrailerMovie);
    }
    public boolean isTrailerDisplayed() {
        try {
            waitForVisibilityOfElementLocated(byFrameVideo);
            return driver.findElement(byFrameVideo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickBuyTicketAtMovie(String movie){
        By byImageMovie = By.xpath("//div[contains(@style, '" + movie +"')]");
        By byBtnBookTicket = By.xpath("//div[contains(@style, '" + movie +"')]/following-sibling::div//a");
        hover(byImageMovie);
        click(byBtnBookTicket);
    }
    public void clickCinemaLogo(String brandCinema) {
        By byCinemaLogo = By.xpath("//button[.//img[contains(@alt, '" + brandCinema.toLowerCase() + "')]]");
        click(byCinemaLogo);
    }
    public void selectCinemaBranch(String cinemaBranch){
        By byCinemaBranch = By.xpath("//button[.//h4[contains(text(),'" + cinemaBranch +"')]]");
        click(byCinemaBranch);
    }

    public boolean isShowTimeInFuture() {
        waitForVisibilityOfElementLocated(byListShowTimeByCinema);
        List<WebElement> links = driver.findElements(byListShowTimeByCinema);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (WebElement link : links) {
            // Ghép thành chuỗi datetime
            String datetext = link.findElement(byDateText).getText();
            String timeText = link.findElement(byTimeText).getText();
            String dateTimeText = datetext + " " + timeText;
            // Parse thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, formatter);
            dateTimes.add(dateTime);
        }
        LocalDateTime now = LocalDateTime.now();
        for (LocalDateTime time : dateTimes){
            if (!time.isAfter(now)){
                return false;
            }
        }
        return true;
    }
    public void selectShowTime(String movieName, String date, String time){
        By byShowTimeOption = By.xpath("//h2[contains(., '" + movieName +"')]/following-sibling::div//a[.//p[contains(text(),'" + date +"')]and .//h3[contains(text(),'" + time +"')]]");
        click(byShowTimeOption);
    }
    public boolean isShowtimeListUnique(String movieName) {
        By byShowTimesByMovie = By.xpath("//h2[contains(., '" + movieName +"')]/following-sibling::div//a[.//p[contains(text(),'-')]and .//h3[contains(text(),':')]]");
        waitForVisibilityOfElementLocated(byShowTimesByMovie);
        List<WebElement> listTime = driver.findElements(byShowTimesByMovie);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        // Date format trong UI
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        for (WebElement time : listTime) {
            // Ghép thành chuỗi datetime
            String datetext = time.findElement(byDateText).getText();
            String timeText = time.findElement(byTimeText).getText();
            String dateTimeText = datetext + " " + timeText;
            // Parse thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, formatter);
            dateTimes.add(dateTime);
        }
        return dateTimes.size() == new HashSet<>(dateTimes).size();
    }
    public boolean isCinemaBelongToSystem(String brandCinema){
        List<WebElement> elements = driver.findElements(byListCinemaNames);
        List<String> listCinema = new ArrayList<>();
        // return elements.stream().map(WebElement::getText).collect(Collectors.toList());
        for (WebElement e : elements) {
            listCinema.add(e.getText());
        }
        for (String c : listCinema){
            if (!c.toLowerCase().contains(brandCinema)){
                return false;
            }
        }
        return true;
    }
    public void buyTicketAtListCinema(String brandCinema,String cinemaBranch, String movieName, String date, String time){
        clickCinemaLogo(brandCinema);
        selectCinemaBranch(cinemaBranch);
        selectShowTime(movieName,date,time);

    }
    public void buyTicketByFilter(String movieName, String cinemaBranch, String showTime){
        selectMovie(movieName);
        selectCinema(cinemaBranch);
        selectDate(showTime);
        clickBuyTicket();
    }
    public void buyTicketAtListMovie(String movie, String cinemaLogo, String cinemaBranch, String date, String time){
        detailMoviePage = new DetailMoviePage(driver);
        clickBuyTicketAtMovie(movie);
        detailMoviePage.clickCinemaLogo(cinemaLogo);
        detailMoviePage.selectShowTime(cinemaBranch,date,time);
    }






}
