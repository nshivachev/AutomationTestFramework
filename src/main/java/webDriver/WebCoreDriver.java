package webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import strategyDesignPattern.*;
import webElement.Element;
import webElement.LogElement;
import webElement.WebCoreElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WebCoreDriver extends Driver {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private BrowserMobProxyServer proxyServer;

    @Override
    public void start(Browser browser) {
        proxyServer = new BrowserMobProxyServer();
        proxyServer.start();
        proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxyServer.newHar();
        String proxyDetails = "127.0.0.1:" + proxyServer.getPort();
        final Proxy proxyConfig = new Proxy()
                .setHttpProxy(proxyDetails)
                .setSslProxy(proxyDetails);

        webDriver = switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                final ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.setProxy(proxyConfig);
                chromeOptions.setAcceptInsecureCerts(true);
                yield new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                final FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-search-engine-choice-screen");
                firefoxOptions.setProxy(proxyConfig);
                firefoxOptions.setAcceptInsecureCerts(true);
                yield new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                yield new SafariDriver();
            }
            case INTERNET_EXPLORER -> {
                WebDriverManager.iedriver().setup();
                yield new InternetExplorerDriver();
            }
            default -> throw new IllegalArgumentException("No such browser " + browser.name());
        };

        proxyServer.blacklistRequests("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)", 400);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    @Override
    public void quit() {
        webDriver.quit();
        proxyServer.abort();
    }

    @Override
    public void goToUrl(String url) {
        webDriver.navigate().to(url);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public Element findById(String id) {
        return find(new IdFindStrategy(id));
    }

    @Override
    public Element findByXPath(String xpath) {
        return find(new XPathFindStrategy(xpath));
    }

    @Override
    public Element findByTag(String tag) {
        return find(new TagFindStrategy(tag));
    }

    @Override
    public Element findByClass(String cssClass) {
        return find(new ClassFindStrategy(cssClass));
    }

    @Override
    public Element findByCss(String css) {
        return find(new CssFindStrategy(css));
    }

    @Override
    public Element findByLinkText(String linkText) {
        return find(new LinkTextFindStrategy(linkText));
    }

    @Override
    public List<Element> findAllById(String id) {
        return findAll(new IdFindStrategy(id));
    }

    @Override
    public List<Element> findAllByXPath(String xpath) {
        return findAll(new XPathFindStrategy(xpath));
    }

    @Override
    public List<Element> findAllByTag(String tag) {
        return findAll(new TagFindStrategy(tag));
    }

    @Override
    public List<Element> findAllByClass(String cssClass) {
        return findAll(new ClassFindStrategy(cssClass));
    }

    @Override
    public List<Element> findAllByCss(String css) {
        return findAll(new CssFindStrategy(css));
    }

    @Override
    public List<Element> findAllByLinkText(String linkText) {
        return findAll(new LinkTextFindStrategy(linkText));
    }

    @Override
    public Element find(FindStrategy findStrategy) {
        var nativeWebElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(findStrategy.convert()));
        Element element = new WebCoreElement(webDriver, nativeWebElement, findStrategy.convert());
        Element logElement = new LogElement(element);

        return logElement;
    }

    @Override
    public List<Element> findAll(FindStrategy findStrategy) {
        List<WebElement> nativeWebElements = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(findStrategy.convert()));
        var elements = new ArrayList<Element>();

        nativeWebElements.forEach(nativeWebElement -> {
            Element element = new WebCoreElement(webDriver, nativeWebElement, findStrategy.convert());
            Element logElement = new LogElement(element);
            elements.add(logElement);
        });

        return elements;
    }

    @Override
    public void waitForAjax() {
        var javascriptExecutor = (JavascriptExecutor) webDriver;
        webDriverWait.until(d -> (Boolean) javascriptExecutor.executeScript("return window.jQuery != undefined && jQuery.active == 0"));
    }

    @Override
    public void waitUntilPageLoadsCompletely() {
        var javascriptExecutor = (JavascriptExecutor) webDriver;
        webDriverWait.until(d -> javascriptExecutor.executeScript("return document.readyState").toString().equals("complete"));
    }

    @Override
    public void addCookie(String cookieName, String cookieValue, String path) {
        Cookie cookie = new Cookie.Builder(cookieName, cookieValue)
                .path(path)
                .build();

        webDriver.manage().addCookie(cookie);
    }

    @Override
    public void deleteAllCookies() {
        webDriver.manage().deleteAllCookies();
    }

    @Override
    public void deleteCookie(String cookieName) {
        webDriver.manage().deleteCookieNamed(cookieName);
    }

    @Override
    public List<Cookie> getAllCookies() {
        return new ArrayList<>(webDriver.manage().getCookies());
    }

    @Override
    public String getCookie(String cookieName) {
        Cookie cookie = webDriver.manage().getCookieNamed(cookieName);

        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    @Override
    public void handle(Function<Object, Alert> function, DialogButton dialogButton) {
        Alert alert = function.apply(webDriver);

        if (alert != null) {
            switch (dialogButton) {
                case ACCEPT:
                    alert.accept();
                    break;
                case DISMISS:
                    alert.dismiss();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown DialogButton: " + dialogButton);
            }
        } else {
            System.out.println("No alert present.");
        }
    }
}
