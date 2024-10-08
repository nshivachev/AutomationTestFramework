package webDriver;

import strategyDesignPattern.IdContainingFindStrategy;
import webElement.Element;

public class ElementFindServiceExtensions {

    public static Element findByIdContaining(ElementFindService elementFindService, String idContaining) {
        return LoggingSingletonDriver.getInstance().find(new IdContainingFindStrategy(idContaining));
    }
}
