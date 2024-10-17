import appDesignPattern.App;
import org.testng.annotations.Test;
import pages.mainPage.MainPage;
import webDriver.Browser;

public class AppTest {

//    private static App app;
//
//    @BeforeClass
//    public void beforeClass() {
//        app = new App(Browser.CHROME);
//    }
//
//    @AfterClass
//    public void afterClass() {
//        app.close();
//    }

    @Test
    public void test1() {
        try (var app = new App(Browser.CHROME)) {
            var mainPage = app.goTo(MainPage.class);

//            app.getBrowserService().waitForAjax();
//            app.getBrowserService().waitUntilPageLoadsCompletely();
            mainPage.assertions().assertUserNameField();
        }
    }

//    @Test
//    public void test2() {
//        try (var app = new App(Browser.CHROME)) {
//            app.goTo(MainPage.class);
//
//            app.getBrowserService().waitForAjax();
//            app.getBrowserService().waitUntilPageLoadsCompletely();
//            app.create(MainPage.class).assertions().assertUserNameField();
//        }
//    }
}
