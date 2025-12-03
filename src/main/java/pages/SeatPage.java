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
    public SeatPage(WebDriver driver) {
        super(driver);
    }
    public void selectSeat(int numberSeat) {
        By bySeatAvailable = By.xpath("//button[@type='button' and not(@disabled)]/span[text()='"+numberSeat+"']");
        click(bySeatAvailable);
    }
    public String getSeatColor(int numberSeat) {
        By bySeatAvailable = By.xpath("//button[@type='button' and not(@disabled)]/span[text()='"+numberSeat+"']");
        return driver.findElement(bySeatAvailable).getCssValue("background-color");
    }

}
