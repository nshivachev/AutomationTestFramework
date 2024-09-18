package listeners;

import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExecutionSubject implements TestExecutionSubject {

    private final List<TestBehaviorObserver> testBehaviorObservers;

    public ExecutionSubject() {
        testBehaviorObservers = new ArrayList<>();
    }

    @Override
    public void attach(TestBehaviorObserver observer) {
        testBehaviorObservers.add(observer);
    }

    @Override
    public void detach(TestBehaviorObserver observer) {
        testBehaviorObservers.remove(observer);
    }

    @Override
    public void preTestInit(ITestResult result, Method memberInfo) {
        testBehaviorObservers.forEach(observer -> observer.preTestInit(result, memberInfo));
    }

    @Override
    public void postTestInit(ITestResult result, Method memberInfo) {
        testBehaviorObservers.forEach(observer -> observer.postTestInit(result, memberInfo));
    }

    @Override
    public void preTestCleanup(ITestResult result, Method memberInfo) {
        testBehaviorObservers.forEach(observer -> observer.preTestCleanup(result, memberInfo));
    }

    @Override
    public void postTestCleanup(ITestResult result, Method memberInfo) {
        testBehaviorObservers.forEach(observer -> observer.postTestCleanup(result, memberInfo));
    }

    @Override
    public void testInstantiated(Method memberInfo) {
        testBehaviorObservers.forEach(observer -> observer.testInstantiated(memberInfo));
    }
}
