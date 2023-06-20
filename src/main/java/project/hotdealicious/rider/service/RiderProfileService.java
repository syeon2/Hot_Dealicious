package project.hotdealicious.rider.service;

import java.sql.Timestamp;

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

	public Rider findRider(Long id) {
		return riderDAO.findById(id);
	}

	public void update(Long id, UpdateRiderDto updateRiderDto) {
		Rider findRider = riderDAO.findById(id);
		String salt = findRider.getSalt();
		String encryptPassword = Sha256Util.getEncrypt(updateRiderDto.getPassword(), salt);

		updateRiderDto.setPassword(encryptPassword);
		updateRiderDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		riderDAO.update(id, updateRiderDto);
	}

	public void updateWorkStatus(Long id, WorkStatus workStatus) {
		riderDAO.updateWorkStatus(id, WorkStatus.changedStatus(workStatus));
	}

	public void withdraw(Long id) {
		riderDAO.delete(id);
	}
}
