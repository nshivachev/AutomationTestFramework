package pages;

import webDriver.ElementFindService;

public abstract class BaseAssertions<ElementsT extends BaseElements> {

    protected final ElementFindService elementFindService;

    public BaseAssertions(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }

    protected ElementsT elements() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 0, elementFindService);
    }
}
