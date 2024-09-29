package pages;

import webDriver.ElementFindService;

public abstract class BaseElements {

    protected final ElementFindService elementFindService;

    public BaseElements(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }
}
