package project.hotdealicious.owner.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.error.exception.LoginException;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;

@Service
@RequiredArgsConstructor
public class OwnerLoginService {

	private final IOwnerDAO ownerDAO;

	public Owner login(Long id, String password) {
		Optional<Owner> findOwnerOptional = ownerDAO.findById(id);

		if (findOwnerOptional.isEmpty()) {
			throw new LoginException("아이디가 존재하지 않습니다.");
		}

		String salt = findOwnerOptional.get().getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		Optional<Owner> comparedPasswordOwner = findOwnerOptional.filter(
			owner -> owner.getPassword().equals(encryptPassword));

		if (comparedPasswordOwner.isEmpty()) {
			throw new LoginException("비밀번호가 일치하지 않습니다.");
		}
		return comparedPasswordOwner.get();
	}
}
