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
        System.out.println("Element text = " + element.getText());
        return element.getText();
    }

    @Override
    public boolean isEnabled() {
        System.out.println("Element enabled = " + element.isEnabled());
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        System.out.println("Element displayed = " + element.isDisplayed());
        return element.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        System.out.println("Type text = " + text);
        element.typeText(text);
    }

    @Override
    public void click() {
        System.out.println("Element clicked");
        element.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        System.out.println("Element's attribute = " + attributeName);
        return element.getAttribute(attributeName);
    }

    @Override
    public void waitToExists() {
        System.out.println("Wait for element To exists");
        element.waitToExists();
    }
}
