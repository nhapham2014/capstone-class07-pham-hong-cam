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

    public SeatPage(WebDriver driver) {
        super(driver);
    }

    public void selectSeat(int numberSeat) {
        By bySeatAvailable = By.xpath("//button[@type='button' and not(@disabled)]/span[text()='"+numberSeat+"']");
        waitForElementToBeClickable(bySeatAvailable);
        click(bySeatAvailable);
    }
    public String getSeatColor(int numberSeat) {
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
        for (WebElement seat : listSeat){
            seat.getAttribute("style").contains("green");
            listSeat.add(seat);

        }
        if(result.size()==listSeat.size()){
            return true;
        }
        return false;


    }

}
