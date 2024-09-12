package WebElement;

import org.openqa.selenium.By;

public class LogElement extends ElementDecorator {

    public LogElement(Element element) {
        super(element);
    }

    @Override
    public By by() {
        return element.by();
    }

    @Override
    public String getText() {
        System.out.println(STR."Element Text = \{element.getText()}");
        return element.getText();
    }

    @Override
    public boolean isEnabled() {
        System.out.println(STR."Element Enabled = \{element.isEnabled()}");
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        System.out.println(STR."Element Displayed = \{element.isDisplayed()}");
        return element.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        System.out.println(STR."Type Text = \{text}");
        element.typeText(text);
    }

    @Override
    public void click() {
        System.out.println("Element Clicked");
        element.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        System.out.println(STR."Element's Attribute = \{attributeName}");
        return element.getAttribute(attributeName);
    }
}
