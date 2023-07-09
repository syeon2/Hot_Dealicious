package project.hotdealicious.customer.dao.mybatis;

import java.sql.Timestamp;
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
	public Optional<Customer> findById(Long id) {
		return customerMapper.findById(id);
	}

	@Override
	public Optional<Customer> findByLoginId(String email) {
		return customerMapper.findByLoginId(email);
	}

	@Override
	public Long update(Long id, UpdateCustomerDto updateCustomerDto) {
		updateCustomerDto.setUpdatedAt(getCurrentTime());

		customerMapper.update(id, updateCustomerDto);

		return id;
	}

	@Override
	public Long delete(Long id) {
		customerMapper.delete(id);

		return id;
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
}
