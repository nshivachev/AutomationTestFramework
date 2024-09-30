package pages;

public class AssertableBasePage<ElementsT extends BaseElements, AssertionsT extends BaseAssertions<ElementsT>> extends BasePage<ElementsT> {

    public AssertableBasePage() {
        super();
    }

    public AssertionsT assertions() {
        return NewInstanceFactory.createByTypeParameter(getClass(), 1, elementFindService);
    }
}
