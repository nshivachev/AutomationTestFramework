package pages;

import webDriver.ElementFindService;
import webDriver.NavigationService;

public abstract class NavigationBasePage<ElementT extends BaseElements> extends BasePage<ElementT> {

    private final NavigationService navigationService;

    public NavigationBasePage(ElementFindService elementFindService, NavigationService navigationService) {
        super(elementFindService);
        this.navigationService = navigationService;
    }

    public void open() {
        navigationService.goToUrl(getUrl());
        waitForPageLoad();
    }

    protected abstract String getUrl();

    protected abstract void waitForPageLoad();
}
