package project.hotdealicious.rider.service;

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

		String salt = findRider.getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		return findRider.getPassword().equals(encryptPassword) ? findRider : null;
	}
}
