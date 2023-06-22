package project.hotdealicious.customer.web;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.CustomerLoginDto;
import project.hotdealicious.customer.service.CustomerLoginService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerLoginController {

	private final CustomerLoginService customerLoginService;

	@PostMapping("/login")
	public Customer login(HttpSession session, @Valid @RequestBody CustomerLoginDto customerLoginDto) {
		Optional<Customer> loginCustomer = customerLoginService.login(customerLoginDto.getEmail(),
			customerLoginDto.getPassword());

		if (loginCustomer.isEmpty()) {
			throw new NoSuchElementException("비밀번호가 맞지 않습니다.");
		}

		Customer customer = loginCustomer.get();
		SessionUtil.setLoginKey(session, SessionUtil.LOGIN_CUSTOMER_KEY, customer.getId());

		return customer;
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginKey(session, SessionUtil.LOGIN_CUSTOMER_KEY);
	}
}
