package pages;

import webDriver.LoggingSingletonDriver;
import webDriver.NavigationService;

public abstract class NavigationAssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends AssertableBasePage<ElementsT, AssertionsT> {

    private final NavigationService navigationService;

    public NavigationAssertableBasePage() {
        super();
        this.navigationService = LoggingSingletonDriver.getInstance();
    }

    public void open() {
        navigationService.goToUrl(getUrl());
        waitForPageLoad();
    }

    protected abstract String getUrl();

    protected abstract void waitForPageLoad();
}
