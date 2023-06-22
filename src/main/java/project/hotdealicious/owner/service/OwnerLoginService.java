package project.hotdealicious.owner.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;

@Service
@RequiredArgsConstructor
public class OwnerLoginService {

	private final IOwnerDAO ownerDAO;

	public Optional<Owner> login(Long id, String password) {
		Optional<Owner> findOwnerOptional = ownerDAO.findById(id);

		if (findOwnerOptional.isEmpty()) {
			throw new NoSuchElementException("아이디가 존재하지 않습니다.");
		}

		String salt = findOwnerOptional.get().getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		return findOwnerOptional.filter(owner -> owner.getPassword().equals(encryptPassword));
	}
}
