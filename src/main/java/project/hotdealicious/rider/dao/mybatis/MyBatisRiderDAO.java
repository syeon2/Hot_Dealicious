package project.hotdealicious.rider.dao.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.domain.WorkStatus;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;

@Repository
@RequiredArgsConstructor
public class MyBatisRiderDAO implements IRiderDAO {

	private final RiderMapper riderMapper;

	public Long save(SaveRiderDto saveRiderDto) {
		riderMapper.save(saveRiderDto);
		return saveRiderDto.getId();
	}

	public Optional<Rider> findById(Long id) {
		return riderMapper.findById(id);
	}

	public Long update(Long id, UpdateRiderDto updateRiderDto) {
		riderMapper.update(id, updateRiderDto);

		return id;
	}

	public Long updateWorkStatus(Long id, WorkStatus workStatus) {
		riderMapper.updateWorkStatus(id, workStatus);

		return id;
	}

	public Long delete(Long id) {
		riderMapper.delete(id);

		return id;
	}
}
