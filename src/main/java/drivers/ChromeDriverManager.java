package drivers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption(
                "excludeSwitches",
                new String[]{"enable-automation"}
        );
        options.setExperimentalOption("useAutomationExtension", false);

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        this.driver = new ChromeDriver(options);
        return driver;
    }
}
