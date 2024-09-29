package pages.mainPage;

import org.openqa.selenium.By;
import pages.BaseElements;
import webDriver.ElementFindService;
import webElement.Element;

public class MainPageElements extends BaseElements {

    public MainPageElements(ElementFindService elementFindService) {
        super(elementFindService);
    }

    public Element userNameField() {
        return elementFindService.findElement(By.id("username"));
    }
}
