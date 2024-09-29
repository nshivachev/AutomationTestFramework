package webDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import webElement.Element;

import java.util.List;
import java.util.function.Function;

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
    public String getCurrentUrl() {
        System.out.println("Current URL = " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
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
    public void addCookie(String cookieName, String cookieValue, String path) {
        System.out.println("Add cookie " + cookieName);
        driver.addCookie(cookieName, cookieValue, path);

    }

    @Override
    public void deleteAllCookies() {
        System.out.println("Delete all cookies");
        driver.deleteAllCookies();
    }

    @Override
    public void deleteCookie(String cookieName) {
        System.out.println("Delete cookie " + cookieName);
        driver.deleteCookie(cookieName);

    }

    @Override
    public List<Cookie> getAllCookies() {
        System.out.println("Get all cookies");
        return driver.getAllCookies();
    }

    @Override
    public String getCookie(String cookieName) {
        System.out.println("Get cookie " + cookieName);
        return driver.getCookie(cookieName);
    }

    @Override
    public void handle(Function<Object, Alert> function, DialogButton dialogButton) {
        System.out.println("Handle " + function);
        driver.handle(function, dialogButton);
    }
}
