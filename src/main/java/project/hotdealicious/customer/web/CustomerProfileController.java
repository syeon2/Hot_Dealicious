package project.hotdealicious.customer.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;
import project.hotdealicious.customer.service.CustomerProfileService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerProfileController {

	private final CustomerProfileService customerProfileService;

	@GetMapping
	public Long join(@Valid @RequestBody SaveCustomerDto saveCustomerDto) {
		return customerProfileService.join(saveCustomerDto);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @Valid @RequestBody UpdateCustomerDto updateCustomerDto) {
		customerProfileService.update(id, updateCustomerDto);
	}

	@DeleteMapping("/{id}")
	public void withdraw(@PathVariable Long id) {
		customerProfileService.withdraw(id);
	}
}
