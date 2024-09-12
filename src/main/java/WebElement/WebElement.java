package WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElement extends Element {

    private final WebDriver webDriver;
    private final org.openqa.selenium.WebElement webElement;
    private final By by;

    public WebElement(WebDriver webDriver, org.openqa.selenium.WebElement webElement, By by) {
        this.webDriver = webDriver;
        this.webElement = webElement;
        this.by = by;
    }

    @Override
    public By by() {
        return by;
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    @Override
    public void click() {
        waitToBeClickable(by);
        webElement.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        return webElement.getAttribute(attributeName);
    }

    private void waitToBeClickable(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
