package pages;

import webDriver.ElementFindService;
import webDriver.LoggingSingletonDriver;

public abstract class BasePage<ElementsT extends BaseElements> {

    protected final ElementFindService elementFindService;

    public BasePage() {
        this.elementFindService = LoggingSingletonDriver.getInstance();
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, elementFindService);
    }
}
