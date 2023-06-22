package project.hotdealicious.rider.service;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.domain.WorkStatus;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;

@Service
@RequiredArgsConstructor
public class RiderProfileService {

	private final IRiderDAO riderDAO;

	public Long join(SaveRiderDto saveRiderDto) {
		String salt = Sha256Util.generateSalt();
		saveRiderDto.setSalt(salt);

		String encryptPassword = Sha256Util.getEncrypt(saveRiderDto.getPassword(), salt);
		saveRiderDto.setPassword(encryptPassword);

		return riderDAO.save(saveRiderDto);
	}

	public Long update(Long id, UpdateRiderDto updateRiderDto) {
		Optional<Rider> findRiderOptional = riderDAO.findById(id);

		if (findRiderOptional.isEmpty()) {
			throw new NoSuchElementException("아이디가 올바르지 않습니다.");
		}

		String salt = findRiderOptional.get().getSalt();
		String encryptPassword = Sha256Util.getEncrypt(updateRiderDto.getPassword(), salt);

		updateRiderDto.setPassword(encryptPassword);
		updateRiderDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		return riderDAO.update(id, updateRiderDto);
	}

	public Long updateWorkStatus(Long id, WorkStatus workStatus) {
		return riderDAO.updateWorkStatus(id, WorkStatus.changedStatus(workStatus));
	}

	public Long withdraw(Long id) {
		return riderDAO.delete(id);
	}
}
