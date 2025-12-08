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
    private By byLbDuration = By.xpath(".//h3[starts-with(normalize-space(),'Thời lượng:')]");
    private By byLbTotal = By.xpath(".//h3[starts-with(normalize-space(),'Giá vé:')]");
    private By byLbCinemaBranch = By.xpath(".//h1[contains(@class, 'colorSecondary')]");
    private By byLbScreenID = By.xpath(".//h3[starts-with(normalize-space(),'Rạp')]");
    private By byLbSeatID = By.xpath(".//h3[starts-with(normalize-space(),'Ghế số:')]");
    public int getNumberOfTicketsInHistory(){
        waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        return listTickets.size();
    }
    public String getLatestTicketDate(){
        waitForVisibilityOfElementLocated(byListTicketHistory);
        List<WebElement> listTickets = driver.findElements(byListTicketHistory);
        if(listTickets.isEmpty()){
            return "";
        }
        WebElement latestTicket = listTickets.get(0);

        String dateOrderText = latestTicket.findElement(byLbDateOrder).getText();
        return dateOrderText.replace("Ngày đặt: ","").trim();
    }
}
