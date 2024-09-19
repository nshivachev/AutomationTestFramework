import listeners.BrowserLaunchTestBehaviorObserver;
import listeners.ExecutionSubject;
import listeners.TestExecutionSubject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import webDriver.Driver;
import webDriver.LoggingDriver;
import webDriver.WebCoreDriver;

public class BaseTest {

    private static final ThreadLocal<TestExecutionSubject> executionSubject = ThreadLocal.withInitial(ExecutionSubject::new);
    private static final ThreadLocal<Driver> driver = ThreadLocal.withInitial(() -> new LoggingDriver(new WebCoreDriver()));
    private ITestResult result;

    static {
        executionSubject.set(new ExecutionSubject());
        driver.set(new LoggingDriver(new WebCoreDriver()));
        new BrowserLaunchTestBehaviorObserver(executionSubject.get(), driver.get());
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
        return driver.get();
    }

    @AfterSuite
    public void afterSuite() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) throws NoSuchMethodException {
        setTestResult(result);
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.get().preTestInit(getTestResult(), methodInfo);
        testInit();
        executionSubject.get().postTestInit(getTestResult(), methodInfo);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws NoSuchMethodException {
        setTestResult(result);
        var testClass = this.getClass();
        var memberInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.get().preTestCleanup(getTestResult(), memberInfo);
        testCleanup();
        executionSubject.get().postTestCleanup(getTestResult(), memberInfo);
    }

    protected void testInit() {}

    protected void testCleanup() {}
}
