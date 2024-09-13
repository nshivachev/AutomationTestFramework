package WebDriver;

import WebElement.Browser;
import WebElement.Element;
import org.openqa.selenium.By;

import java.util.List;

public class LoggingDriver extends DriverDecorator {

    public LoggingDriver(Driver driver) {
        super(driver);
    }

    @Override
    public void start(Browser browser) {
        System.out.println(STR."Start browser = \{browser.toString()}");
        driver.start(browser);
    }

    @Override
    public void quit() {
        System.out.println("Close browser");
        driver.quit();
    }

    @Override
    public void goToUrl(String url) {
        System.out.println(STR."Go to URL = \{url}");
        driver.goToUrl(url);
    }

    @Override
    public Element findElement(By locator) {
        System.out.println("Find element");
        return driver.findElement(locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        System.out.println("Find elements");
        return driver.findElements(locator);
    }
}
