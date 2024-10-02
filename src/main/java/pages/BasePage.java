package pages;

import webDriver.Driver;
import webDriver.ElementFindService;

public abstract class BasePage<ElementsT extends BaseElements> {

    protected final ElementFindService elementFindService;

    public BasePage(Driver driver) {
        this.elementFindService = driver;
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, elementFindService);
    }
}
