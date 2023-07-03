package project.hotdealicious.menu.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.dto.SaveMenuDto;
import project.hotdealicious.menu.dto.UpdateMenuDto;

@Mapper
public interface MenuMapper {

	Long save(SaveMenuDto saveMenuDto);

	Menu findById(Long id);

	List<Menu> findByShopId(Long storeId);

	void update(@Param("id") Long id, @Param("updateParam") UpdateMenuDto updateMenuDto);

	void delete(Long id);
}
