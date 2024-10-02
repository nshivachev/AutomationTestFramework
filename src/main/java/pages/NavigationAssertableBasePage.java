package pages;

import webDriver.Driver;
import webDriver.NavigationService;

public abstract class NavigationAssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends AssertableBasePage<ElementsT, AssertionsT> {

    private final NavigationService navigationService;

    public NavigationAssertableBasePage(Driver driver) {
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
