package project.hotdealicious.customer.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.CustomerLoginDto;
import project.hotdealicious.login.service.MemberLoginService;
import project.hotdealicious.login.service.UserType;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerLoginController {

	private final MemberLoginService memberLoginService;

	@PostMapping("/login")
	public ApiResult<Customer> loginCustomer(HttpSession session,
		@Valid @RequestBody CustomerLoginDto customerLoginDto) {
		Customer loginCustomer = (Customer)memberLoginService.login(customerLoginDto.getEmail(),
			customerLoginDto.getPassword(), UserType.CUSTOMER);

		SessionUtil.setLoginKey(session, SessionUtil.LOGIN_CUSTOMER_KEY, loginCustomer.getId());

		return ApiResult.onSuccess(loginCustomer);
	}

	@PostMapping("/logout")
	public void logoutCustomer(HttpSession session) {
		SessionUtil.removeLoginKey(session, SessionUtil.LOGIN_CUSTOMER_KEY);
	}
}
