package pages.mainPage;

import pages.NavigationAssertableBasePage;
import singletonFactory.SingletonFactory;
import webDriver.Driver;
import webDriver.LoggingSingletonDriver;

public class MainPage extends NavigationAssertableBasePage<MainPageElements, MainPageAssertions> {

    private MainPage(Driver driver) {
        super(driver);
    }

    public static MainPage getInstance() {
        return SingletonFactory.getInstance(MainPage.class, LoggingSingletonDriver.getInstance());
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
