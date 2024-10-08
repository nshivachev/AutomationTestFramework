package strategyDesignPattern;

import org.openqa.selenium.By;

public class TagFindStrategy extends FindStrategy {

    public TagFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.tagName(getValue());
    }
}
