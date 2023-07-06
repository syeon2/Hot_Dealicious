package project.hotdealicious.menu.dao;

import java.util.List;

import project.hotdealicious.menu.domain.MenuOption;
import project.hotdealicious.menu.dto.SaveMenuOptionDto;
import project.hotdealicious.menu.dto.UpdateMenuOptionDto;

public interface IMenuOptionDAO {

	Long save(SaveMenuOptionDto saveMenuOptionDto);

	List<MenuOption> findMenuOptions(Long menuId);

	void update(Long id, UpdateMenuOptionDto updateMenuOptionDto);

	void delete(Long id);
}
