package Test;

import org.zkoss.zk.ui.Executions;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	
	public static void setCookie(String username, String password) {
        try {
            //add cookie
            HttpServletResponse response = (HttpServletResponse)Executions.getCurrent().getNativeResponse();
            Cookie userCookie = new Cookie("myopenjournal_user", username);
            Cookie passCookie = new Cookie("myopenjournal_password", password);
            response.addCookie(userCookie);
            response.addCookie(passCookie);
             

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static String getCookie() {
        //get cookie
        Cookie [] cookies = ((HttpServletRequest)Executions.getCurrent().getNativeRequest()).getCookies();
		return cookies[0].getValue();
	}

}
