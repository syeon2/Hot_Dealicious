package project.hotdealicious.owner.dao;

import java.util.Optional;

import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

public interface IOwnerDAO {

	Long save(SaveOwnerDto saveOwnerDto);

	Optional<Owner> findById(Long id);

	Long update(Long id, UpdateOwnerDto updateOwnerDto);

	Long delete(Long id);
}
