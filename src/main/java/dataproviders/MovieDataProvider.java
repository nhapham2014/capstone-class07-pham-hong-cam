package dataproviders;

import org.testng.annotations.DataProvider;

public class MovieDataProvider {
    @DataProvider(name = "movieNameData")
    public static Object[][] movieNameData() {
        return new Object[][]{
                {"avatar-2_gp09", "AVATAR 2"}
        };
    }
    @DataProvider(name = "verifyDataAtCinemaAndSeatPage")
    public static Object[][] verifyDataAtCinemaAndSeatPage() {
        return new Object[][]{
                {"avatar-2_gp09", "AVATAR 2", "cgv", "CGV - VivoCity", "07-10-2021", "08:25", "Lầu 5, Trung tâm thương mại SC VivoCity - 1058 Nguyễn Văn Linh, Q. 7"}
        };
    }
}
