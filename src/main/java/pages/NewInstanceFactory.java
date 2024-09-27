package pages;

import webDriver.Driver;

import java.lang.reflect.ParameterizedType;

public class NewInstanceFactory {

    public static <T> T createByTypeParameter(Class parameterClass, int index, Driver driver) {
        try {
            var elementClass = (Class)((ParameterizedType)parameterClass.getGenericSuperclass()).getActualTypeArguments()[index];
            return (T)elementClass.getDeclaredConstructor(Driver.class).newInstance(driver);

        } catch (Exception e) {
            return null;
        }
    }
}
