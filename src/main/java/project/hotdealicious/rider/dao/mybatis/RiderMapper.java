package project.hotdealicious.rider.dao.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.domain.WorkStatus;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;

@Mapper
public interface RiderMapper {

	Long save(SaveRiderDto saveRiderDto);

	Optional<Rider> findById(Long id);

	void update(@Param("id") Long id, @Param("updateParam") UpdateRiderDto updateRiderDto);

	void updateWorkStatus(@Param("id") Long id, @Param("workStatus") WorkStatus workStatus);

	void delete(@Param("id") Long id);
}
