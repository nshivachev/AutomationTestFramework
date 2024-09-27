package pages.mainPage;

import pages.NavigationAssertableBasePage;
import webDriver.Driver;

public class MainPage extends NavigationAssertableBasePage<MainPageElements, MainPageAssertions> {

    public MainPage(Driver driver) {
        super(driver);
    }

    @Override
    protected String getUrl() {
        return "https://abv.bg";
    }

    @Override
    protected void waitForPageLoad() {
        elements().userNameField().waitToExists();
    }
}
