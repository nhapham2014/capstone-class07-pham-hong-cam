package dataproviders;

import org.testng.annotations.DataProvider;

public class FilterMovieDataProvider {
    @DataProvider(name = "filterMovieData")
    public static Object[][] filterMovieData() {
        return new Object[][]{
                {"AVATAR 2", "CGV - Golden Plaza", "15/10/2021 ~ 08:42"}
        };
    }
    @DataProvider(name = "otherMovieData")
    public static Object[][] otherMovieData() {
        return new Object[][]{
                {"AVATAR 2", "CGV - Golden Plaza", "15/10/2021 ~ 08:42", "Man of Steel"}
        };
    }
    @DataProvider(name = "onlyMovieData")
    public static Object[][] onlyMovieData() {
        return new Object[][]{
                {"AVATAR 2"}
        };
    }
    @DataProvider(name = "verifyWithoutSelectShowtime")
    public static Object[][] verifyWithoutSelectShowtime() {
        return new Object[][]{
                {"AVATAR 2", "CGV - Golden Plaza"}
        };
    }
    @DataProvider(name = "verifyDataOnSeatPage")
    public static Object[][] verifyDataOnSeatPage() {
        return new Object[][]{
                {"AVATAR 2", "CGV - Golden Plaza", "15/10/2021 ~ 08:42", "15/10/2021", "08:42"}
        };
    }
}
