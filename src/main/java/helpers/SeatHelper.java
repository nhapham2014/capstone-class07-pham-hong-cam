package helpers;

import org.openqa.selenium.WebElement;

public class SeatHelper {
    public static boolean isSeatDisabled(WebElement seat) {
        return !seat.isEnabled()
                || seat.getAttribute("disabled") != null
                || seat.getAttribute("class").contains("disabled");
    }
}
