package pages;

import webDriver.ElementFindService;

import java.lang.reflect.ParameterizedType;

public class NewInstanceFactory {

    public static <T> T createByTypeParameter(Class parameterClass, int index, ElementFindService elementFindService) {
        try {
            var elementClass = (Class)((ParameterizedType)parameterClass.getGenericSuperclass()).getActualTypeArguments()[index];
            return (T)elementClass.getDeclaredConstructor(ElementFindService.class).newInstance(elementFindService);

        } catch (Exception e) {
            return null;
        }
    }
}
