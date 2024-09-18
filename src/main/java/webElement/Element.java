package webElement;

import org.openqa.selenium.By;

public abstract class Element {
    public abstract By getBy();

    public abstract String getText();

    public abstract boolean isEnabled();

    public abstract boolean isDisplayed();

    public abstract void typeText(String text);

    public abstract void click();

    public abstract String getAttribute(String attributeName);
}
