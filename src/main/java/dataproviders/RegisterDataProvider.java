package dataproviders;

import org.testng.annotations.DataProvider;

import java.util.UUID;

public class RegisterDataProvider {
    @DataProvider(name = "registerDataSuccess", parallel = true)
    public static Object[][] registerDataSuccess() {
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        return new Object[][]{

                {newAcount,"Diqit0505@", "Diqit0505@","Pham Thanh Nha",newAcount + "@example.com"}
        };
    }
    @DataProvider(name = "registerWithMismatchedPassword", parallel = true)
    public static Object[][] registerWithMismatchedPassword(){
        return new Object[][]{

                {"","Diqit0505@", "Diqit0506@","Pham Thanh Nha",""}
        };
    }
    @DataProvider(name = "registerWithExistingAccount", parallel = true)
    public static Object[][] registerWithExistingAccount(){
        return new Object[][]{

                {"cam0592", "Diqit0505@", "Diqit0505@", "Pham Thanh Nha", "cam997789@gmail.com"}
        };
    }
    @DataProvider(name = "registerWithExistingEmail", parallel = true)
    public static Object[][] registerWithExistingEmail(){
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        return new Object[][]{

                {newAcount, "Diqit0505@", "Diqit0505@", "Pham Thanh Nha", "cam@gmail.com"}
        };
    }
    @DataProvider(name = "registerWithWeakPassword", parallel = true)
    public static Object[][] registerWithWeakPassword(){
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        return new Object[][]{

                {newAcount, "123", "123", "Pham Thanh Nha", newAcount + "@example.com"}
        };
    }
}
