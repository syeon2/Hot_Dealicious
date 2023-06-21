package project.hotdealicious.owner.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

@Service
@RequiredArgsConstructor
public class OwnerProfileService {

	private final IOwnerDAO ownerDAO;

	public Long join(SaveOwnerDto saveOwnerDto) {
		String salt = Sha256Util.generateSalt();
		String encryptPassword = Sha256Util.getEncrypt(saveOwnerDto.getPassword(), salt);

		saveOwnerDto.setSalt(salt);
		saveOwnerDto.setPassword(encryptPassword);

		return ownerDAO.save(saveOwnerDto);
	}

	public void update(Long id, UpdateOwnerDto updateOwnerDto) {
		Owner findOwner = ownerDAO.findById(id);
		String encryptPassword = getEncryptPassword(updateOwnerDto.getPassword(), findOwner);

		updateOwnerDto.setPassword(encryptPassword);
		updateOwnerDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		ownerDAO.update(id, updateOwnerDto);
	}

	public void withdraw(Long id) {
		ownerDAO.delete(id);
	}

	private String getEncryptPassword(String password, Owner findOwner) {
		String salt = findOwner.getSalt();

		return Sha256Util.getEncrypt(password, salt);
	}
}