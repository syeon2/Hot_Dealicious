package project.hotdealicious.menu.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.dao.IMenuDAO;
import project.hotdealicious.menu.dao.IMenuOptionDAO;
import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.domain.MenuOption;
import project.hotdealicious.menu.dto.MenuDetailDto;
import project.hotdealicious.menu.dto.SaveMenuDto;
import project.hotdealicious.menu.dto.UpdateMenuDto;

@Service
@RequiredArgsConstructor
public class MenuManageService {

	private final IMenuDAO menuDAO;
	private final IMenuOptionDAO menuOptionDAO;

	public Long registerMenu(SaveMenuDto saveMenuDto) {
		return menuDAO.save(saveMenuDto);
	}

	public MenuDetailDto findMenuDetail(Long id) {
		Menu findMenu = menuDAO.findById(id);
		List<MenuOption> menuOptions = menuOptionDAO.findMenuOptions(id);

		return new MenuDetailDto(findMenu, menuOptions);
	}

	public List<Menu> findMenusInShop(Long shopId) {
		return menuDAO.findByShopId(shopId);
	}

	public void updateMenu(Long id, UpdateMenuDto updateMenuDto) {
		updateMenuDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		menuDAO.update(id, updateMenuDto);
	}

	public void withdraw(Long id) {
		menuDAO.delete(id);
	}
}
