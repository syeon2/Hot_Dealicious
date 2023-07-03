package project.hotdealicious.menu.dao;

import java.util.List;

import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.dto.SaveMenuDto;
import project.hotdealicious.menu.dto.UpdateMenuDto;

public interface IMenuDAO {

	Long save(SaveMenuDto saveMenuDto);

	Menu findById(Long id);

	List<Menu> findByShopId(Long storeId);

	void update(Long id, UpdateMenuDto updateMenuDto);

	void delete(Long id);
}
