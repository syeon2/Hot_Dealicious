package project.hotdealicious.owner.dao;

import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

public interface IOwnerDAO {

	Long save(SaveOwnerDto saveOwnerDto);

	Owner findById(Long id);

	void update(Long id, UpdateOwnerDto updateOwnerDto);

	void delete(Long id);
}
