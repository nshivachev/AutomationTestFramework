package pages;

import webDriver.Driver;

public abstract class BaseElements {

    protected final Driver driver;

    public BaseElements(Driver driver) {
        this.driver = driver;
    }
}
