package pages.mainPage;

import lombok.experimental.ExtensionMethod;
import pages.BaseElements;
import webDriver.ElementFindService;
import webDriver.ElementFindServiceExtensions;
import webElement.Element;

@ExtensionMethod(ElementFindServiceExtensions.class)
public class MainPageElements extends BaseElements {

    public MainPageElements(ElementFindService elementFindService) {
        super(elementFindService);
    }

    public Element userNameField() {
//        return elementFindService.findById("username");
        return elementFindService.findByIdContaining("username");
    }
}
