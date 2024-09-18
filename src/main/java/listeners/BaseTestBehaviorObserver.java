package listeners;

import org.testng.ITestResult;

import java.lang.reflect.Method;

public class BaseTestBehaviorObserver implements TestBehaviorObserver {

    public BaseTestBehaviorObserver(TestExecutionSubject testExecutionSubject) {
        testExecutionSubject.attach(this);
    }

    @Override
    public void preTestInit(ITestResult testResult, Method memberInfo) {
    }

    @Override
    public void postTestInit(ITestResult testResult, Method memberInfo) {
    }

    @Override
    public void preTestCleanup(ITestResult testResult, Method memberInfo) {
    }

    @Override
    public void postTestCleanup(ITestResult testResult, Method memberInfo) {
    }

    @Override
    public void testInstantiated(Method memberInfo) {
    }
}
