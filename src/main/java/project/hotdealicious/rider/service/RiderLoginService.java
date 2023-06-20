package project.hotdealicious.rider.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;

@Service
@RequiredArgsConstructor
public class RiderLoginService {

	private final IRiderDAO riderDAO;

	public Rider login(Long id, String password) {
		Rider findRider = riderDAO.findById(id);

		if (findRider == null) {
			throw new NoSuchElementException("아이디 또는 비밀번호가 밎지 않습니다.");
		}

		String salt = findRider.getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		return findRider.getPassword().equals(encryptPassword) ? findRider : null;
	}
}
