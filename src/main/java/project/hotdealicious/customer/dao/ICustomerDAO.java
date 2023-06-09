package project.hotdealicious.customer.dao;

import java.util.Optional;

import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

public interface ICustomerDAO {

	Long save(SaveCustomerDto saveCustomerDto);

	Customer findById(Long id);

	Optional<Customer> findByLoginId(String email);

	void update(Long id, UpdateCustomerDto updateCustomerDto);

	void delete(Long id);
}
