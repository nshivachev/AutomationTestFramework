package WebElement;

import org.openqa.selenium.By;

public abstract class ElementDecorator extends Element {

    protected final Element element;

    protected ElementDecorator(Element element) {
        this.element = element;
    }

    @Override
    public By by() {
        return element.by();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        element.typeText(text);
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        return element.getAttribute(attributeName);
    }
}
