package project.hotdealicious.customer.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;
import project.hotdealicious.customer.service.CustomerProfileService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerProfileController {

	private final CustomerProfileService customerProfileService;

	@PostMapping
	public ApiResult<Long> join(@Valid @RequestBody SaveCustomerDto saveCustomerDto) {
		Long customerId = customerProfileService.join(saveCustomerDto);
		return ApiResult.onSuccess(customerId);
	}

	@PostMapping("/{id}")
	public ApiResult<Long> updateCustomerInfo(@PathVariable Long id,
		@Valid @RequestBody UpdateCustomerDto updateCustomerDto) {
		Long customerId = customerProfileService.update(id, updateCustomerDto);
		return ApiResult.onSuccess(customerId);
	}

	@DeleteMapping("/{id}")
	public ApiResult<Long> withdrawCustomer(@PathVariable Long id) {
		Long customerId = customerProfileService.remove(id);
		return ApiResult.onSuccess(customerId);
	}
}
