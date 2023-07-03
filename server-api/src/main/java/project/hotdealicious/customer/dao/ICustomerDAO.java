package project.hotdealicious.customer.dao;

import java.util.Optional;

import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

public interface ICustomerDAO {

	Long save(SaveCustomerDto saveCustomerDto);

	Optional<Customer> findById(Long id);

	Optional<Customer> findByLoginId(String email);

	Long update(Long id, UpdateCustomerDto updateCustomerDto);

	Long delete(Long id);
}
