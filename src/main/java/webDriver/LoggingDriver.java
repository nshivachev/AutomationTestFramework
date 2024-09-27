package webDriver;

import org.openqa.selenium.By;
import webElement.Element;

import java.util.List;

public class LoggingDriver extends DriverDecorator {

    public LoggingDriver(Driver driver) {
        super(driver);
    }

    @Override
    public void start(Browser browser) {
        System.out.println("Start browser = " + browser.name());
        driver.start(browser);
    }

    @Override
    public void quit() {
        System.out.println("Close browser");
        driver.quit();
    }

    @Override
    public void goToUrl(String url) {
        System.out.println("Go to URL = " + url);
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

    @Override
    public void waitForAjax() {
        System.out.println("Wait for ajax");
        driver.waitForAjax();
    }

    @Override
    public void waitUntilPageLoadsCompletely() {
        System.out.println("Wait for page loads complete");
        driver.waitUntilPageLoadsCompletely();
    }

    @Override
    public void deleteAllCookies() {
        System.out.println("Delete all cookies");
        this.driver.deleteAllCookies();
    }
}
