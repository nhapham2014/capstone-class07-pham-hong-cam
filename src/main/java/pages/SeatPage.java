package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScenarioContext;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SeatPage extends CommonPage {
    private By byLbSeat = By.xpath("//h3[span[contains(text(),'Ghế ')]]");
    private By byListSeatOnTicket = By.xpath("//span[contains(text(),'Ghế ')]");
    private By byListSeat = By.xpath("//button[@type='button']");
    private By byLbPrice = By.xpath(" //p[contains(normalize-space(), 'VND')]");
    private By byLbCinemaBrach = By.xpath("//div[h3[contains(text(),'Cụm Rạp:')]]");
    private By byLbAddress = By.xpath("//div[h3[contains(text(),'Địa chỉ:')]]");
    private By byLbScreenID = By.xpath("//h3[normalize-space()='Rạp:']/following-sibling::h3");
    private By byLbDate = By.xpath("//div[h3[contains(text(),'Ngày giờ chiếu')]]//h3[contains(normalize-space(), ' -')]");
    private By byLbTime = By.xpath("//div[h3[contains(text(),'Ngày giờ chiếu')]]//span");
    private By byLbNameMovie = By.xpath("//div[h3[contains(text(),'Tên Phim:')]]");
    private By byBtnBookTicket = By.xpath("//button[span[contains(text(),'ĐẶT VÉ')]]");
    private By byMsgError = By.xpath("//div[div[contains(@class,'error')]]/h2");
    private By byMsgSuccess = By.xpath("//div[div[contains(@class,'success')]]/h2");
    private By byBtnAgree = By.xpath("//button[contains(text(),'Đồng ý')]");

    public SeatPage(WebDriver driver) {
        super(driver);


    }
    public void selectSeat(String numberSeat) {
        By bySeatAvailable = By.xpath("//button[@type='button' and not(@disabled)]/span[text()='" + numberSeat + "']");
        waitUtil.waitForElementToBeClickable(bySeatAvailable);
        click(bySeatAvailable);
    }
    public String selectRandomSeat() {
        Random rand = new Random();

        while (true) {
            int seatNumber = rand.nextInt(160) + 1; // từ 1 → 160
            String seat = String.format("%02d", seatNumber); // format 2 số: 01, 02, ...

            // XPATH tìm đúng ghế theo số
            By seatLocator = By.xpath("//button[span[text()='" + seat + "']]");

            List<WebElement> elements = driver.findElements(seatLocator);

            // Nếu ghế KHÔNG render = disabled
            if (elements.isEmpty()) {
                continue; // thử random ghế khác
            }

            WebElement e = elements.get(0);

            // Kiểm tra disable (3 loại)
            boolean disabled =
                    !e.isEnabled() ||
                            e.getAttribute("disabled") != null ||
                            e.getAttribute("class").contains("disabled");

            if (!disabled) {
                e.click();
                return seat; // trả về số ghế đã chọn
            }
        }
    }

    public List<String> selectRandomSeats(int count) {
        List<String> selectedSeats = new ArrayList<>();

        while (selectedSeats.size() < count) {
            String seat = selectRandomSeat();

            if (!selectedSeats.contains(seat)) {
                selectedSeats.add(seat);
            }
        }

        return selectedSeats;
    }

    public String getSeatColor(String numberSeat) {
        By bySeatAvailable = By.xpath("//button[.//span[text()='" + numberSeat + "']]");
        waitUtil.waitForVisibilityOfElementLocated(bySeatAvailable);
        return driver.findElement(bySeatAvailable).getAttribute("style").replace("background-color: ","").replace(";", "");
    }

    public String getSeatID() {
        return getText(byLbSeat).replace(",", "").replace("Ghế ", "").trim();

    }
    public List<WebElement> listSelectedSeat(){
        List<WebElement> listSeat = driver.findElements(byListSeat);
        List<WebElement> listSelectedSeat = new ArrayList<>();
        for (WebElement seat : listSeat){
            if (seat.getAttribute("style").contains("green")) {
                listSelectedSeat.add(seat);
            }
        }
        return listSelectedSeat;
    }

    public int getDisplayedPriceSeat() {
        waitUtil.waitForVisibilityOfElementLocated(byLbPrice);
        String price = getText(byLbPrice).replace("\n", "").trim();
        price = price.replace("VND", "").trim().replace(".", "");
        return Integer.parseInt(price);
    }

    public boolean isVipSeat(String seatNumber) {
        int num = Integer.parseInt(seatNumber);

        return (num >= 35 && num <= 46) ||
                (num >= 51 && num <= 62) ||
                (num >= 67 && num <= 78) ||
                (num >= 83 && num <= 94) ||
                (num >= 99 && num <= 110) ||
                (num >= 115 && num <= 126);
    }

    public int getSelectedVIPSeatCount() {
        List<WebElement> listVIPSeat = new ArrayList<>();
        for (WebElement element : listSelectedSeat()) {
            if (isVipSeat(element.getText().trim())) {
                listVIPSeat.add(element);
            }
        }
        return listVIPSeat.size();
    }
    public int getSelectedRegularSeatCount() {
        List<WebElement> listRegularSeat = new ArrayList<>();
        for (WebElement element : listSelectedSeat()) {
            if (!isVipSeat(element.getText().trim())) {
                listRegularSeat.add(element);
            }
        }
        return listRegularSeat.size();
    }
    public int totalPrice(){
        int priceVIPSeat = 90000;
        int priceRegularSeat = 75000;
        return ((getSelectedVIPSeatCount()*priceVIPSeat) + (getSelectedRegularSeatCount()*priceRegularSeat));
    }
    public String getNameCinemaBranch(){
        return getText(byLbCinemaBrach).replace("Cụm Rạp:", "").trim();
    }
    public String getAddressCinema(){
        return getText(byLbAddress).replace("Địa chỉ:", "").trim();
    }
    public String getScreenID(){
        return getText(byLbScreenID).replace("Rạp:","").trim();
    }
    public String getDateOfShowTime(){
        waitUtil.waitForVisibilityOfElementLocated(byLbDate);
        return getText(byLbDate).replace(" -","").replace(getText(byLbTime),"").replace("/","-").trim();

    }
    public String getTimeOfShowTime(){
        waitUtil.waitForVisibilityOfElementLocated(byLbTime);
        return getText(byLbTime).trim();
    }

    public String getMovieName(){
        return getText(byLbNameMovie).replace("Tên Phim:","").trim();
    }
    public List<String> getListSelectedSeatOnTicket(){
        waitUtil.waitForVisibilityOfElementLocated(byLbCinemaBrach);
        List<WebElement> listSeatTicket = driver.findElements(byListSeatOnTicket);
        List<String> listSeat = new ArrayList<>();
        for (WebElement seat : listSeatTicket){
            listSeat.add(seat.getText().replace("Ghế ","").replace(",","").trim());
        }
        return listSeat;
    }
    public boolean isDisplayCorrectSeatOnTicket(){
        List<String> listSeatTicket = getListSelectedSeatOnTicket();
        List<String> listSelectedSeat = listSelectedSeat().stream()
                .map(e -> e.getText().trim().replace(",", ""))
                    .collect(Collectors.toList());
        return listSeatTicket.containsAll(listSelectedSeat)
                && listSelectedSeat.containsAll(listSeatTicket);
    }
    public void clickBookTicketButton(){
        waitUtil.waitForElementToBeClickable(byBtnBookTicket);
        click(byBtnBookTicket);


    }
    public String getErrorMessage() {
        waitUtil.waitForVisibilityOfElementLocated(byMsgError);
        return getText(byMsgError).trim();
    }
    public String getSuccessMessage() {
        waitUtil.waitForVisibilityOfElementLocated(byMsgSuccess);
        return getText(byMsgSuccess).trim();
    }
    public void clickAgreeButtonInAlert(){
        waitUtil.waitForElementToBeClickable(byBtnAgree);
        click(byBtnAgree);

    }
    public boolean isDisableSeat(String numberSeat){
        waitUtil.waitForVisibilityOfElementLocated(byLbSeat);
        List<WebElement> seats = driver.findElements(
                By.xpath("//button[span[text()='" + numberSeat + "']]")
        );
        if(seats.isEmpty()) {
            return true; // không render -> coi như disabled
        }

        WebElement seat = seats.get(0);

        return !seat.isEnabled()
                || seat.getAttribute("disabled") != null
                || seat.getAttribute("class").contains("disabled");
    }



}
