package project.hotdealicious.customer.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.dto.SaveCustomerDto;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

	private final ICustomerDAO customerDAO;

	public Long join(SaveCustomerDto saveCustomerDto) {
		return customerDAO.save(saveCustomerDto);
	}

	public void withdraw(Long id) {
		customerDAO.delete(id);
	}
}
