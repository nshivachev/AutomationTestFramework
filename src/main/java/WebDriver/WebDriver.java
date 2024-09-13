package WebDriver;

import WebElement.Browser;
import WebElement.Element;
import WebElement.LogElement;
import WebElement.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebDriver extends Driver {

    private org.openqa.selenium.WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @Override
    public void start(Browser browser) {
        switch (browser) {
            case Chrome -> {
                System.setProperty("webdriver.chrome.driver", String.valueOf(Path.of(System.getProperty("user.dir"), "drivers", "chromedriver.exe")));
                webDriver = new ChromeDriver();
            }
            case Firefox -> webDriver = new FirefoxDriver();
            case Edge -> webDriver = new EdgeDriver();
            case Safari -> webDriver = new SafariDriver();
            case InternetExplorer -> webDriver = new InternetExplorerDriver();
            default -> throw new IllegalArgumentException(STR."No such browser \{browser}");
        }

        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public void goToUrl(String url) {
        webDriver.navigate().to(url);
    }

    @Override
    public Element findElement(By locator) {
        org.openqa.selenium.WebElement nativeWebElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Element element = new WebElement(webDriver, nativeWebElement, locator);
        Element logElement = new LogElement(element);

        return logElement;
    }

    @Override
    public List<Element> findElements(By locator) {
        List<org.openqa.selenium.WebElement> nativeWebElements = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        List<Element> elements = new ArrayList<>();
        nativeWebElements.forEach(e -> {
            Element element = new WebElement(webDriver, e, locator);
            elements.add(element);
        });

        return elements;
    }
}
