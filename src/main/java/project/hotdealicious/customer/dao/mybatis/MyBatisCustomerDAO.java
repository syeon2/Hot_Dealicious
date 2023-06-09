package project.hotdealicious.customer.dao.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

@Repository
@RequiredArgsConstructor
public class MyBatisCustomerDAO implements ICustomerDAO {

	private final CustomerMapper customerMapper;

	@Override
	public Long save(SaveCustomerDto saveCustomerDto) {
		customerMapper.save(saveCustomerDto);
		return saveCustomerDto.getId();
	}

	@Override
	public Customer findById(Long id) {
		return customerMapper.findById(id);
	}

	@Override
	public Optional<Customer> findByLoginId(String email) {
		return customerMapper.findByLoginId(email);
	}

	@Override
	public void update(Long id, UpdateCustomerDto updateCustomerDto) {
		customerMapper.update(id, updateCustomerDto);
	}

	@Override
	public void delete(Long id) {
		customerMapper.delete(id);
	}
}
