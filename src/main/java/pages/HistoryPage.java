package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryPage extends CommonPage {
    public HistoryPage(WebDriver driver) {
        super(driver);
    }
    private By byListTicketHistory = By.xpath("//div[h3[starts-with(normalize-space(),'Ngày đặt:')]]/ancestor::div[contains(@class,'MuiGrid-item')]");
    private By byLbDateOrder = By.xpath(".//h3[starts-with(normalize-space(),'Ngày đặt:')]");
    private By byLbMovieName = By.xpath(".//h1[starts-with(normalize-space(),'Tên phim:')]");
    private By byLbTotal = By.xpath(".//h3[starts-with(normalize-space(),'Giá vé:')]");
    private By byLbCinemaBranch = By.xpath(".//h1[contains(@class, 'colorSecondary')]");
    private By byLbScreenID = By.xpath(".//h3[starts-with(normalize-space(),'Rạp')]");
    private By byLbSeatID = By.xpath(".//h3[starts-with(normalize-space(),'Ghế số:')]");
    public int getNumberOfBookingInHistory(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        return listTickets.size();
    }
    public String getLatestTicketDate(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return "";
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String dateOrderText = latestTicket.findElement(byLbDateOrder).getText().replace("Ngày đặt: ","").replace("|","").replaceAll("\\s+", " ").trim();
        return dateOrderText;
    }
    public String getLatestTicketMovieName(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return "";
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String movieNameText = latestTicket.findElement(byLbMovieName).getText().replace("Tên phim: ","").trim();
        return movieNameText;
    }
    public int getLatestTicketTotalPrice(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return 0;
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String totalPrice = latestTicket.findElement(byLbTotal).getText().replace("\n", "").trim();
        totalPrice= totalPrice.replace("Giá vé: ","").replace(" VND","").trim().replace(".", "");
        return Integer.parseInt(totalPrice);
    }
    public String getLatestTicketCinemaBranch(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return "";
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String cinemaBranchText = latestTicket.findElement(byLbCinemaBranch).getText().trim();
        return cinemaBranchText;
    }
    public String getLatestTicketScreenID(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return "";
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String screenIDText = latestTicket.findElement(byLbScreenID).getText().replace("Rạp: ","").trim();
        return screenIDText;
    }
    public List<String> getLatestListSeat(){
        waitUtil.waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return new ArrayList<>();
        }
        WebElement latestTicket = listTickets.get(listTickets.size()-1);
        scrollToElement(latestTicket);

        String seatText = latestTicket.findElement(byLbSeatID).getText().replace("Ghế số:","").trim();
        List<String> listSeat = Arrays.stream(seatText.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        return listSeat;
    }
}
