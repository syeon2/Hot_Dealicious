package project.hotdealicious.customer.dao.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

@Mapper
public interface CustomerMapper {

	void save(SaveCustomerDto saveCustomerDto);

	Customer findById(Long id);

	Optional<Customer> findByLoginId(String email);

	void update(@Param("id") Long id, @Param("updateParam") UpdateCustomerDto updateCustomerDto);

	void delete(Long id);
}
