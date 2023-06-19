package project.hotdealicious.owner.dao.mybatis;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

@Repository
@RequiredArgsConstructor
public class MyBatisOwnerDAO implements IOwnerDAO {

	private final OwnerMapper ownerMapper;

	@Override
	public Long save(SaveOwnerDto saveOwnerDto) {
		ownerMapper.save(saveOwnerDto);
		return saveOwnerDto.getId();
	}

	@Override
	public Owner findById(Long id) {
		return ownerMapper.findById(id);
	}

	@Override
	public void update(Long id, UpdateOwnerDto updateOwnerDto) {
		ownerMapper.update(id, updateOwnerDto);
	}

	@Override
	public void delete(Long id) {
		ownerMapper.delete(id);
	}
}
