package project.hotdealicious.customer.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.CustomerLoginDto;
import project.hotdealicious.customer.dto.ResponseLogoutDTO;
import project.hotdealicious.customer.service.CustomerLoginService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerLoginController {

	private final CustomerLoginService customerLoginService;

	@PostMapping("/login")
	public Customer login(HttpSession session, @Valid @RequestBody CustomerLoginDto customerLoginDto) {
		Customer loginCustomer = customerLoginService.login(customerLoginDto.getEmail(),
			customerLoginDto.getPassword());

		SessionUtil.setLoginCustomerId(session, loginCustomer.getEmail(), loginCustomer.getId());

		return loginCustomer;
	}

	@GetMapping("/logout")
	public ResponseLogoutDTO logout(HttpSession session) {
		SessionUtil.removeLoginCustomerId(session);

		return new ResponseLogoutDTO();
	}
}
