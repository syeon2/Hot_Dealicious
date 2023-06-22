package project.hotdealicious.rider.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;

@Service
@RequiredArgsConstructor
public class RiderLoginService {

	private final IRiderDAO riderDAO;

	public Optional<Rider> login(Long id, String password) {
		Optional<Rider> findRiderOptional = riderDAO.findById(id);

		if (findRiderOptional.isEmpty()) {
			throw new NoSuchElementException("아이디가 존재하지 않습니다.");
		}

		String salt = findRiderOptional.get().getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		return findRiderOptional.filter(rider -> rider.getPassword().equals(encryptPassword));
	}
}
