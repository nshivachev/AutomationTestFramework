package pages;

import webDriver.LoggingSingletonDriver;
import webDriver.NavigationService;

public abstract class NavigationBasePage<ElementT extends BaseElements> extends BasePage<ElementT> {

    private final NavigationService navigationService;

    public NavigationBasePage() {
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
