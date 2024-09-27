package pages;

import webDriver.Driver;

public class AssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends BasePage<ElementsT> {

    public AssertableBasePage(Driver driver) {
        super(driver);
    }

    public AssertionsT assertions() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 1, driver);
    }
}
