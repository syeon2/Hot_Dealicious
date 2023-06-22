package project.hotdealicious.customer.service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
		String password = saveCustomerDto.getPassword();

		password = Sha256Util.getEncrypt(password, salt);

		saveCustomerDto.setSalt(salt);
		saveCustomerDto.setEncryptedPassword(password);

		return customerDAO.save(saveCustomerDto);
	}

	public Long update(Long id, UpdateCustomerDto updateCustomerDto) {
		Optional<Customer> findCustomerOptional = customerDAO.findById(id);

		if (findCustomerOptional.isEmpty()) {
			throw new NoSuchElementException("아이디를 확인해주세요.");
		}

		Customer findCustomer = findCustomerOptional.get();

		String encryptPassword = Sha256Util.getEncrypt(updateCustomerDto.getPassword(), findCustomer.getSalt());
		updateCustomerDto.setPassword(encryptPassword);

		return customerDAO.update(findCustomer.getId(), updateCustomerDto);
	}

	public Long withdraw(Long id) {
		return customerDAO.delete(id);
	}
}
