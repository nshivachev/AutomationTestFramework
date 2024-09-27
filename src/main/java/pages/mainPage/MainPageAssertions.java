package pages.mainPage;

import org.testng.Assert;
import pages.BaseAssertions;
import webDriver.Driver;

public class MainPageAssertions extends BaseAssertions<MainPageElements> {

    public MainPageAssertions(Driver driver) {
        super(driver);
    }

    public void assertUserNameField() {
        Assert.assertTrue(elements().userNameField().isDisplayed(), "Username field is not displayed");
    }
}
