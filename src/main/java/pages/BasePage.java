package pages;

import webDriver.Driver;

//Page for common elements as main menu, search bar
public abstract class BasePage<ElementsT extends BaseElements> {

    protected final Driver driver;

    public BasePage(Driver driver) {
        this.driver = driver;
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, driver);
    }
}
