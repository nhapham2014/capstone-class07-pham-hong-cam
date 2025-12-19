package base;

public class BaseTestWithLogin extends BaseTest {
    @Override
    protected boolean needLogin() {
        return true;
    }
}
