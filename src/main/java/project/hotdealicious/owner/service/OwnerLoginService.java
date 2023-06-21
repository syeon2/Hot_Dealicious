package project.hotdealicious.owner.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;

@Service
@RequiredArgsConstructor
public class OwnerLoginService {

	private final IOwnerDAO ownerDAO;

	public Owner login(Long id, String password) {
		Owner findOwner = ownerDAO.findById(id);

		String salt = findOwner.getSalt();
		String encryptPassword = Sha256Util.getEncrypt(password, salt);

		return findOwner.getPassword().equals(encryptPassword) ? findOwner : null;
	}
}
