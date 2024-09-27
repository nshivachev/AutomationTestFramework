package pages.mainPage;

import org.openqa.selenium.By;
import pages.BaseElements;
import webDriver.Driver;
import webElement.Element;

public class MainPageElements extends BaseElements {

    public MainPageElements(Driver driver) {
        super(driver);
    }

    public Element userNameField() {
        return driver.findElement(By.id("username"));
    }
}
