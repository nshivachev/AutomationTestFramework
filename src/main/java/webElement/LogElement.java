package webElement;

import org.openqa.selenium.By;

public class LogElement extends ElementDecorator {

    public LogElement(Element element) {
        super(element);
    }

    @Override
    public By getBy() {
        return element.getBy();
    }

    @Override
    public String getText() {
        System.out.println("Element Text = " + element.getText());
        return element.getText();
    }

    @Override
    public boolean isEnabled() {
        System.out.println("Element Enabled = " + element.isEnabled());
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        System.out.println("Element Displayed = " + element.isDisplayed());
        return element.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        System.out.println("Type Text = " + text);
        element.typeText(text);
    }

    @Override
    public void click() {
        System.out.println("Element Clicked");
        element.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        System.out.println("Element's Attribute = " + attributeName);
        return element.getAttribute(attributeName);
    }
}
