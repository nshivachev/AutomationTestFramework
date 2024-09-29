package pages.mainPage;

import pages.NavigationAssertableBasePage;
import webDriver.ElementFindService;
import webDriver.NavigationService;

public class MainPage extends NavigationAssertableBasePage<MainPageElements, MainPageAssertions> {

    public MainPage(ElementFindService elementFindService, NavigationService navigationService) {
        super(elementFindService, navigationService);
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
