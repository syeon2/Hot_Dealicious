package project.hotdealicious.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import project.hotdealicious.common.util.SessionUtil;

public class CustomerLoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		HttpSession session = request.getSession(false);

		return checkLoginTarget(session);
	}

	private boolean checkLoginTarget(HttpSession session) {
		return session.getAttribute(SessionUtil.LOGIN_CUSTOMER_KEY) != null;
	}
}
