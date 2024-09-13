package WebDriver;

import WebElement.Browser;
import WebElement.Element;
import org.openqa.selenium.By;

import java.util.List;

public abstract class DriverDecorator extends Driver {

    protected final Driver driver;

    protected DriverDecorator(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void start(Browser browser) {
        this.driver.start(browser);
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public void goToUrl(String url) {
        this.driver.goToUrl(url);
    }

    @Override
    public Element findElement(By locator) {
        return this.driver.findElement(locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        return this.driver.findElements(locator);
    }
}
