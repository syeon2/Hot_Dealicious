package project.hotdealicious.customer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;

@Service
@RequiredArgsConstructor
public class CustomerLoginService {

	private final ICustomerDAO customerDAO;

	public Customer login(String email, String password) {
		Optional<Customer> findCustomerOptional = customerDAO.findByLoginId(email);

		return findCustomerOptional.filter(customer -> customer.getPassword().equals(password)).orElse(null);
	}
}
