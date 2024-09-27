import listeners.BrowserBehavior;
import listeners.ExecutionBrowser;
import org.testng.annotations.Test;
import pages.mainPage.MainPage;
import webDriver.Browser;

@ExecutionBrowser(browser = Browser.CHROME, browserBehavior = BrowserBehavior.RESTART_ON_FAIL)
public class TestClass extends BaseTest {

    private static MainPage mainPage;

    @Override
    public void testInit() {
        mainPage = new MainPage(getDriver());
    }

    @Test(priority = 1)
    public void test1() {
        mainPage.open();
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        mainPage.assertions().assertUserNameField();
    }

//    @Test(priority = 2)
//    public void test2() {
//        getDriver().goToUrl("https://abv.bg");
//    }
}
