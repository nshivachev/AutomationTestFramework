import listeners.BrowserBehavior;
import listeners.ExecutionBrowser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import webDriver.Browser;
import webElement.Element;

@ExecutionBrowser(browser = Browser.CHROME, browserBehavior = BrowserBehavior.RESTART_ON_FAIL)
public class TestClass extends BaseTest {

    @Test(priority = 1)
    public void test1() {
        getDriver().goToUrl("https://abv.bg");
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        Element usernameField = getDriver().findElement(By.id("username"));
        Assert.assertTrue(usernameField.isDisplayed());
    }

    @Test(priority = 2)
    public void test2() {
        getDriver().goToUrl("https://abv.bg");
    }
}
