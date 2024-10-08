package strategyDesignPattern;

import org.openqa.selenium.By;

public class XPathFindStrategy extends FindStrategy {

    public XPathFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.xpath(getValue());
    }
}
