package pages;

import webDriver.Driver;

public abstract class BaseAssertions<ElementsT extends BaseElements> {

    protected final Driver driver;

    public BaseAssertions(Driver driver) {
        this.driver = driver;
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, driver);
    }
}
