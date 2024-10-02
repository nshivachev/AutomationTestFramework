package appDesignPattern;

import pages.BasePage;
import pages.NavigationAssertableBasePage;
import singletonFactory.SingletonFactory;
import webDriver.*;

public class App implements AutoCloseable {

    private Boolean disposed = false;

    public App(Browser browserType) {
        LoggingSingletonDriver.getInstance().start(browserType);
    }

    public NavigationService getNavigationService() {
        return LoggingSingletonDriver.getInstance();
    }

    public BrowserService getBrowserService() {
        return LoggingSingletonDriver.getInstance();
    }

    public CookiesService getCookiesService() {
        return LoggingSingletonDriver.getInstance();
    }

    public DialogService getDialogService() {
        return SingletonFactory.getInstance(DialogService.class);
    }

    public <TPage extends NavigationAssertableBasePage> TPage goTo(Class<TPage> pageOf) {
        var page = SingletonFactory.getInstance(pageOf, LoggingSingletonDriver.getInstance());
        page.open();
        return page;
    }

    public <TPage extends BasePage> TPage create(Class<TPage> pageOf) {
        return SingletonFactory.getInstance(pageOf, LoggingSingletonDriver.getInstance());
    }

    @Override
    public void close() {
        if (disposed) {
            return;
        }

        LoggingSingletonDriver.getInstance().quit();

        disposed = true;
    }
}
