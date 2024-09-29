package pages.mainPage;

import org.testng.Assert;
import pages.BaseAssertions;
import webDriver.ElementFindService;

public class MainPageAssertions extends BaseAssertions<MainPageElements> {

    public MainPageAssertions(ElementFindService elementFindService) {
        super(elementFindService);
    }

    public void assertUserNameField() {
        Assert.assertTrue(elements().userNameField().isDisplayed(), "Username field is not displayed");
    }
}
