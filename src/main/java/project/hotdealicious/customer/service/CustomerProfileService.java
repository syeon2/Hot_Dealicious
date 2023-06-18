package project.hotdealicious.customer.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.customer.dto.SaveCustomerDto;
import project.hotdealicious.customer.dto.UpdateCustomerDto;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

	private final ICustomerDAO customerDAO;

	public Long join(SaveCustomerDto saveCustomerDto) {
		String salt = Sha256Util.generateSalt();

		saveCustomerDto.setSalt(salt);
		String password = saveCustomerDto.getPassword();
		password = Sha256Util.getEncrypt(password, salt);

		saveCustomerDto.setEncryptedPassword(password);

		return customerDAO.save(saveCustomerDto);
	}

	public void update(Long id, UpdateCustomerDto updateCustomerDto) {
		Customer findCustomer = customerDAO.findById(id);

		String encryptPassword = Sha256Util.getEncrypt(updateCustomerDto.getPassword(), findCustomer.getSalt());
		updateCustomerDto.setPassword(encryptPassword);

		customerDAO.update(findCustomer.getId(), updateCustomerDto);
	}

	public void withdraw(Long id) {
		customerDAO.delete(id);
	}
}
