package pages;

import webDriver.Driver;

public abstract class NavigationAssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends AssertableBasePage<ElementsT, AssertionsT> {

    public NavigationAssertableBasePage(Driver driver) {
        super(driver);
    }

    public void open() {
        driver.goToUrl(getUrl());
        waitForPageLoad();
    }

    protected abstract String getUrl();

    protected abstract void waitForPageLoad();
}
