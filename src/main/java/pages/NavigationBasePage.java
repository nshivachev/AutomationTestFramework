package pages;

import webDriver.Driver;
import webDriver.NavigationService;

public abstract class NavigationBasePage<ElementT extends BaseElements> extends BasePage<ElementT> {

    private final NavigationService navigationService;

    public NavigationBasePage(Driver driver) {
        super(driver);
        this.navigationService = driver;
    }

    public void open() {
        navigationService.goToUrl(getUrl());
        waitForPageLoad();
    }

    protected abstract String getUrl();

    protected abstract void waitForPageLoad();
}
