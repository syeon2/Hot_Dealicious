package project.hotdealicious.common.util;

import javax.servlet.http.HttpSession;

public abstract class SessionUtil {

	private static final String LOGIN_CUSTOMER_KEY = "LOGIN_CUSTOMER_KEY";

	public static Long getLoginCustomerId(HttpSession session, String email, Long id) {
		return (Long)session.getAttribute(getSessionKey(email, id));
	}

	public static void setLoginCustomerId(HttpSession session, String email, Long id) {
		session.setAttribute(LOGIN_CUSTOMER_KEY, getSessionKey(email, id));
	}

	public static void removeLoginCustomerId(HttpSession session) {
		session.removeAttribute(LOGIN_CUSTOMER_KEY);
	}

	private static String getSessionKey(String email, Long id) {
		return email.concat(":").concat(String.valueOf(id));
	}
}
