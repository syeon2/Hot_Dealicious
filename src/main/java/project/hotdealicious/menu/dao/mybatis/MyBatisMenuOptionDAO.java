package project.hotdealicious.menu.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.dao.IMenuOptionDAO;
import project.hotdealicious.menu.domain.MenuOption;
import project.hotdealicious.menu.dto.SaveMenuOptionDto;
import project.hotdealicious.menu.dto.UpdateMenuOptionDto;

@Repository
@RequiredArgsConstructor
public class MyBatisMenuOptionDAO implements IMenuOptionDAO {

	private final MenuOptionMapper menuOptionMapper;

	@Override
	public Long save(SaveMenuOptionDto saveMenuOptionDto) {
		menuOptionMapper.save(saveMenuOptionDto);

		return saveMenuOptionDto.getId();
	}

	@Override
	public List<MenuOption> findMenuOptions(Long menuId) {
		return menuOptionMapper.findMenuOptions(menuId);
	}

	@Override
	public void update(Long id, UpdateMenuOptionDto updateMenuOptionDto) {
		menuOptionMapper.update(id, updateMenuOptionDto);
	}

	@Override
	public void delete(Long id) {
		menuOptionMapper.delete(id);
	}
}
