package project.hotdealicious.owner.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

@Mapper
public interface OwnerMapper {

	Long save(SaveOwnerDto saveOwnerDto);

	Owner findById(Long id);

	void update(@Param("id") Long id, @Param("updateParam") UpdateOwnerDto updateOwnerDto);

	void delete(Long id);
}
