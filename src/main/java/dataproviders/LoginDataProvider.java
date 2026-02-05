package dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginDataSuccess", parallel = true)
    public static Object[][] loginDataSuccess() {
        return new Object[][]{

                {"cam9292", "Diqit0505@"}
        };
    }

    @DataProvider(name = "loginDataInvalidWithEmptyUsername", parallel = true)
    public static Object[][] loginDataInvalidWithEmptyUsername() {
        return new Object[][]{
                {"Diqit0505@"}
        };
    }
    @DataProvider(name = "loginDataInvalidWithEmptyPassword", parallel = true)
    public static Object[][] loginDataInvalidWithEmptyPassword() {
        return new Object[][]{
                {"cam"}
        };
    }
    @DataProvider(name = "loginDataInvalidWithIncorrectFormatPassword", parallel = true)
    public static Object[][] loginDataInvalidWithIncorrectFormatPassword() {
        return new Object[][]{
                {"cam9292", "123"}
        };
    }
    @DataProvider(name = "loginDataInvalidWithIncorrectUsername", parallel = true)
    public static Object[][] loginDataInvalidWithIncorrectUsername() {
        return new Object[][]{
                {"unknownUser", "Diqit0505@"}
        };
    }


}
