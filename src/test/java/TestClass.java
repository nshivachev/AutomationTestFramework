import listeners.BrowserBehavior;
import listeners.ExecutionBrowser;
import org.testng.annotations.Test;
import pages.mainPage.MainPage;
import webDriver.*;

@ExecutionBrowser(browser = Browser.CHROME, browserBehavior = BrowserBehavior.RESTART_ON_FAIL)
public class TestClass extends BaseTest {

    private static MainPage mainPage;
    private static BrowserService browserService;

    @Override
    public void testInit() {
        browserService = LoggingSingletonDriver.getInstance();
        mainPage = MainPage.getInstance();
    }

    @Test(priority = 1)
    public void test1() {
        mainPage.open();
        browserService.waitForAjax();
        browserService.waitUntilPageLoadsCompletely();
        mainPage.assertions().assertUserNameField();
    }

//    @Test(priority = 2)
//    public void test2() {
//        getDriver().goToUrl("https://abv.bg");
//    }
}
