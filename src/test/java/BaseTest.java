import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import listeners.BrowserLaunchTestBehaviorObserver;
import listeners.TestExecutionSubject;
import listeners.ExecutionSubject;
import webDriver.Driver;
import webDriver.LoggingDriver;
import webDriver.WebCoreDriver;

public class BaseTest {

    private static final TestExecutionSubject executionSubject;
    private static final Driver driver;
    private ITestResult result;

    static  {
        executionSubject = new ExecutionSubject();
        driver = new LoggingDriver(new WebCoreDriver());
        new BrowserLaunchTestBehaviorObserver(executionSubject, driver);
    }

    public String getTestName() {
        return getTestResult().getTestName();
    }

    public void setTestResult(ITestResult result) {
        this.result = result;
    }

    public ITestResult getTestResult() {
        return result;
    }

    public Driver getDriver() {
        return driver;
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) throws NoSuchMethodException {
        setTestResult(result);
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.preTestInit(getTestResult(), methodInfo);
        testInit();
        executionSubject.postTestInit(getTestResult(), methodInfo);
    }

    @AfterMethod
    public void afterMethod() throws NoSuchMethodException {
        var testClass = this.getClass();
        var memberInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.preTestCleanup(getTestResult(), memberInfo);
        testCleanup();
        executionSubject.postTestCleanup(getTestResult(), memberInfo);
    }

    protected void testInit() {}

    protected void testCleanup() {}
}
