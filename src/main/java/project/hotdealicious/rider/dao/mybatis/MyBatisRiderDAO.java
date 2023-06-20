package project.hotdealicious.rider.dao.mybatis;

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

	public Rider findById(Long id) {
		return riderMapper.findById(id);
	}

	public void update(Long id, UpdateRiderDto updateRiderDto) {
		riderMapper.update(id, updateRiderDto);
	}

	public void updateWorkStatus(Long id, WorkStatus workStatus) {
		riderMapper.updateWorkStatus(id, workStatus);
	}

	public void delete(Long id) {
		riderMapper.delete(id);
	}
}
