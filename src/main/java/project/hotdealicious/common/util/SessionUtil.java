package project.hotdealicious.common.util;

import javax.servlet.http.HttpSession;

public abstract class SessionUtil {

	public static final String LOGIN_CUSTOMER_KEY = "LOGIN_CUSTOMER_KEY";
	public static final String LOGIN_OWNER_KEY = "LOGIN_OWNER_KEY";
	public static final String LOGIN_RIDER_KEY = "LOGIN_RIDER_KEY";

	public static Long getLoginCustomerId(HttpSession session, String email, Long id) {
		return (Long)session.getAttribute(getSessionKey(email, id));
	}

	public static void setLoginKey(HttpSession session, String key, Long value) {
		session.setAttribute(key, value);
	}

	public static void removeLoginKey(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	private static String getSessionKey(String email, Long id) {
		return email.concat(":").concat(String.valueOf(id));
	}
}
