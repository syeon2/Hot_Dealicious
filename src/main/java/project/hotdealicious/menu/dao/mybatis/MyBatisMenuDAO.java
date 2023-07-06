package project.hotdealicious.menu.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.dao.IMenuDAO;
import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.dto.SaveMenuDto;
import project.hotdealicious.menu.dto.UpdateMenuDto;

@Repository
@RequiredArgsConstructor
public class MyBatisMenuDAO implements IMenuDAO {

	private final MenuMapper menuMapper;

	@Override
	public Long save(SaveMenuDto saveMenuDto) {
		menuMapper.save(saveMenuDto);

		return saveMenuDto.getId();
	}

	@Override
	public Menu findById(Long id) {
		return menuMapper.findById(id);
	}

	@Override
	public List<Menu> findByShopId(Long storeId) {
		return menuMapper.findByShopId(storeId);
	}

	@Override
	public void update(Long id, UpdateMenuDto updateMenuDto) {
		menuMapper.update(id, updateMenuDto);
	}

	@Override
	public void delete(Long id) {
		menuMapper.delete(id);
	}
}
