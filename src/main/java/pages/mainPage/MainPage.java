package pages.mainPage;

import pages.NavigationAssertableBasePage;
import singletonFactory.SingletonFactory;

public class MainPage extends NavigationAssertableBasePage<MainPageElements, MainPageAssertions> {

    private MainPage() {
        super();
    }

    public static MainPage getInstance() {
        return SingletonFactory.getInstance(MainPage.class);
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
