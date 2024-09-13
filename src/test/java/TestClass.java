import WebDriver.Driver;
import WebDriver.LoggingDriver;
import WebDriver.WebDriver;
import WebElement.Browser;
import WebElement.Element;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public void test() {
        Driver driver = new LoggingDriver(new WebDriver());
        driver.start(Browser.Chrome);
        driver.goToUrl("https://abv.bg");
        Element usernameField = driver.findElement(By.id("username"));
        Assert.assertTrue(usernameField.isDisplayed());
        usernameField.typeText("testUser");
        driver.quit();
    }
}
