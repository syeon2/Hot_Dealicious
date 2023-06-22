package project.hotdealicious.menu.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.menu.domain.MenuOption;
import project.hotdealicious.menu.dto.SaveMenuOptionDto;
import project.hotdealicious.menu.dto.UpdateMenuOptionDto;

@Mapper
public interface MenuOptionMapper {

	Long save(SaveMenuOptionDto saveMenuOptionDto);

	List<MenuOption> findMenuOptions(Long menuId);

	void update(@Param("id") Long id, @Param("updateParam") UpdateMenuOptionDto updateMenuOptionDto);

	void delete(Long id);
}
