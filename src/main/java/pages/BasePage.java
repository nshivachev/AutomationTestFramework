package pages;

import webDriver.ElementFindService;

public abstract class BasePage<ElementsT extends BaseElements> {

    protected final ElementFindService elementFindService;

    public BasePage(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, elementFindService);
    }
}
