package project.hotdealicious.customer.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;

@Service
@RequiredArgsConstructor
public class CustomerLoginService {

	private final ICustomerDAO customerDAO;

	public Optional<Customer> login(String email, String password) {
		Optional<Customer> findCustomerOptional = customerDAO.findByLoginId(email);

		if (findCustomerOptional.isEmpty()) {
			throw new NoSuchElementException("아이디가 존재하지 않습니다.");
		}

		String salt = findCustomerOptional.get().getSalt();
		String encryptedPassword = Sha256Util.getEncrypt(password, salt);

		return findCustomerOptional.filter(customer -> customer.getPassword().equals(encryptedPassword));
	}
}