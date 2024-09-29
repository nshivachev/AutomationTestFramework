package pages;

import webDriver.ElementFindService;

public class AssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends BasePage<ElementsT> {

    public AssertableBasePage(ElementFindService elementFindService) {
        super(elementFindService);
    }

    public AssertionsT assertions() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 1, elementFindService);
    }
}
