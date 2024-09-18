package listeners;

import webDriver.Browser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionBrowser {

    BrowserBehavior browserBehavior() default BrowserBehavior.RESTART_EVERY_TIME;

    Browser browser() default Browser.CHROME;
}
