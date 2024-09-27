package pages;

import webDriver.Driver;

public abstract class NavigationBasePage<ElementT extends BaseElements> extends BasePage<ElementT> {

    public NavigationBasePage(Driver driver) {
        super(driver);
    }

    public void open() {
        driver.goToUrl(getUrl());
        waitForPageLoad();
    }

    protected abstract String getUrl();

    protected abstract void waitForPageLoad();
}
