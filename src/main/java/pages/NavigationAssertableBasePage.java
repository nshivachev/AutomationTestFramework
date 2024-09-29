package pages;

import webDriver.ElementFindService;
import webDriver.NavigationService;

public abstract class NavigationAssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends AssertableBasePage<ElementsT, AssertionsT> {

    private final NavigationService navigationService;

    public NavigationAssertableBasePage(ElementFindService elementFindService, NavigationService navigationService) {
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
