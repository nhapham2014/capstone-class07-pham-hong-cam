package dataproviders;

import org.testng.annotations.DataProvider;

public class CinemaDataProvider {
    @DataProvider(name = "verifyListCinemaBrandData")
    public static Object[][] verifyListCinemaBrandData() {
        return new Object[][]{
                {"cgv"}
        };
    }

    @DataProvider(name = "verifyShowTimeInFutureData")
    public static Object[][] verifyShowTimeInFutureData() {
        return new Object[][]{
                {"cgv", "CGV - Golden Plaza"}
        };
    }

    @DataProvider(name = "verifyForShowTimeOverLap")
    public static Object[][] verifyForShowTimeOverLap() {
        return new Object[][]{
                {"cgv", "CGV - Golden Plaza", "John Wick"}
        };
    }

    @DataProvider(name = "navigateToSeatPageAfterSelectShowTime")
    public static Object[][] navigateToSeatPageAfterSelectShowTime() {
        return new Object[][]{
                {"cgv", "CGV - Golden Plaza", "John Wick", "08-08-2021", "18:27"}
        };
    }

    @DataProvider(name = "verifyOnTicketInfoAtSeatPage")
    public static Object[][] verifyOnTicketInfoAtSeatPage() {
        return new Object[][]{
                {"cgv", "CGV - Golden Plaza", "John Wick", "08-08-2021", "18:27", "Tầng 4, Trung tâm thương mại Golden Plaza, 922 Nguyễn Trãi, P. 14, Q. 5"}
        };
    }

}
