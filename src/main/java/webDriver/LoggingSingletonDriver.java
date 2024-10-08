package webDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import singletonFactory.SingletonFactory;
import webElement.Element;

import java.util.List;
import java.util.function.Function;

public class LoggingSingletonDriver extends DriverDecorator {

    private static LoggingSingletonDriver instance;

    private LoggingSingletonDriver(Driver driver) {
        super(driver);
    }

    public static LoggingSingletonDriver getInstance() {
        return SingletonFactory.getInstance(LoggingSingletonDriver.class, new WebCoreDriver());
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
    public Element findById(String id) {
        System.out.println("Find element by id = " + id);
        return driver.findById(id);
    }

    @Override
    public Element findByXPath(String xpath) {
        System.out.println("Find element by xpath = " + xpath);
        return driver.findByXPath(xpath);
    }

    @Override
    public Element findByTag(String tag) {
        System.out.println("Find element by tag = " + tag);
        return driver.findByTag(tag);
    }

    @Override
    public Element findByClass(String cssClass) {
        System.out.println("Find element by class = " + cssClass);
        return driver.findByClass(cssClass);
    }

    @Override
    public Element findByCss(String css) {
        System.out.println("Find element by css = " + css);
        return driver.findByCss(css);
    }

    @Override
    public Element findByLinkText(String linkText) {
        System.out.println("Find element by link text = " + linkText);
        return driver.findByLinkText(linkText);
    }

    @Override
    public List<Element> findAllById(String id) {
        System.out.println("Find all elements by id = " + id);
        return driver.findAllById(id);
    }

    @Override
    public List<Element> findAllByXPath(String xpath) {
        System.out.println("Find all elements by xpath = " + xpath);
        return driver.findAllByXPath(xpath);
    }

    @Override
    public List<Element> findAllByTag(String tag) {
        System.out.println("Find all elements by tag = " + tag);
        return driver.findAllByTag(tag);
    }

    @Override
    public List<Element> findAllByClass(String cssClass) {
        System.out.println("Find all elements by class = " + cssClass);
        return driver.findAllByClass(cssClass);
    }

    @Override
    public List<Element> findAllByCss(String css) {
        System.out.println("Find all elements by css = " + css);
        return driver.findAllByCss(css);
    }

    @Override
    public List<Element> findAllByLinkText(String linkText) {
        System.out.println("Find all elements by link text = " + linkText);
        return driver.findAllByLinkText(linkText);
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
