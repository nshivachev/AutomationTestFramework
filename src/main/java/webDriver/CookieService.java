package webDriver;

import org.openqa.selenium.Cookie;

import java.util.List;

public interface CookieService {

    void addCookie(String cookieName, String cookieValue, String path);

    void deleteAllCookies();

    void deleteCookie(String cookieName);

    List<Cookie> getAllCookies();

    String getCookie(String cookieName);
}
