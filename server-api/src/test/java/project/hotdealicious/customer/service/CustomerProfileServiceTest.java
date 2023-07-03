package project.hotdealicious.customer.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

@SpringBootTest
@Sql(scripts = {"classpath:schema_mysql.sql"})
class CustomerProfileServiceTest {

	private static final String MEMBER_ID = "waterkite94@gmail.com";

	@Autowired
	private ICustomerDAO customerDAO;

	@Autowired
	private CustomerProfileService customerProfileService;

	@Test
	@DisplayName("소비자 회원가입 성공")
	void join() {
		// given
		SaveCustomerDto saveCustomerDto = new SaveCustomerDto(MEMBER_ID, "suyeon", "01000001111", "경기도");
		saveCustomerDto.setEncryptedPassword("testing");

		// when
		customerProfileService.join(saveCustomerDto);

		// then
		Optional<Customer> findCustomerOptional = customerDAO.findByLoginId(MEMBER_ID);
		assertThat(findCustomerOptional.get().getEmail()).isEqualTo(MEMBER_ID);
	}

	@Test
	@DisplayName("소비자 회원정보 업데이트")
	void update() {
		// given
		join();
		Customer findCustomer = customerDAO.findByLoginId(MEMBER_ID).get();
		String changedPhone = "01022223333";
		String changedAddress = "강원도";
		String changedPassword = "testing2";

		// when
		UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto(changedPhone, changedAddress);
		updateCustomerDto.setPassword(changedPassword);
		customerProfileService.update(findCustomer.getId(), updateCustomerDto);

		// then
		Customer updateCustomer = customerDAO.findById(findCustomer.getId());
		assertThat(updateCustomer.getPhone()).isEqualTo(changedPhone);
		assertThat(updateCustomer.getAddress()).isEqualTo(changedAddress);
	}
}
