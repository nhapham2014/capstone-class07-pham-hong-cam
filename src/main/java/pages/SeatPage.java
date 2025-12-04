package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class SeatPage extends CommonPage {
    private By byLbSeat = By.xpath("//h3[span[contains(text(),'Ghế ')]]");
    private By byListSeatAtTicket = By.xpath("//span[contains(text(),'Ghế ')]");
    private By byListSeat = By.xpath("//button[@type='button']");
    private By byLbPrice = By.xpath(" //p[contains(normalize-space(), 'VND')]");

    public SeatPage(WebDriver driver) {
        super(driver);
    }

    public void selectSeat(String numberSeat) {
        By bySeatAvailable = By.xpath("//button[@type='button' and not(@disabled)]/span[text()='"+numberSeat+"']");
        waitForElementToBeClickable(bySeatAvailable);
        click(bySeatAvailable);
    }
    public String getSeatColor(String numberSeat) {
        By bySeatAvailable = By.xpath("//button[.//span[text()='"+numberSeat+"']]");
        waitForVisibilityOfElementLocated(bySeatAvailable);
        return driver.findElement(bySeatAvailable).getAttribute("style");
    }
    public String getSeatID(){
        return getText(byLbSeat).replace(",", "");

    }
    public boolean isDisplayCorrectOfNumberSeat(){
        List<WebElement> listSeaTickett=  driver.findElements(byListSeatAtTicket);
        List<WebElement> listSeat = driver.findElements(byListSeat);
        List<WebElement> result = new ArrayList<>();
        for (WebElement seat : listSeat) {
            if (seat.getAttribute("style").contains("green")) {
                result.add(seat);
            }
        }
        if(result.size()==listSeaTickett.size()){
            return true;
        }
        return false;
    }
    public int getDisplayedPriceSeat(){
        waitForVisibilityOfElementLocated(byLbPrice);
        String price = getText(byLbPrice).replace("\n","").trim();
        price=price.replace("VND","").trim().replace(".","");
        return Integer.parseInt(price);
    }
    public int getSelectedRegularSeatCount(String numberSeat){
        By bySeatAvailable = By.xpath("//button[.//span[text()='"+numberSeat+"']]");
       List<WebElement> result = new ArrayList<>();
        List<WebElement> listSeat = driver.findElements(bySeatAvailable);
        for (WebElement seat : listSeat) {
            if (seat.getAttribute("style").contains("green")) {
                result.add(seat);
            }
        }
        if(numberSeat.equals("01")||numberSeat.equals("02")||numberSeat.equals("03")||numberSeat.equals("04")||numberSeat.equals("05")
                ||numberSeat.equals("06")||numberSeat.equals("07")||numberSeat.equals("08")||numberSeat.equals("09")
                ||numberSeat.equals("10")||numberSeat.equals("11")||numberSeat.equals("12")||numberSeat.equals("13")
                ||numberSeat.equals("14")||numberSeat.equals("15")||numberSeat.equals("16")||numberSeat.equals("17")
                ||numberSeat.equals("18")||numberSeat.equals("19")||numberSeat.equals("20")){
            return result.size();
        }
return 0;
    }
}
