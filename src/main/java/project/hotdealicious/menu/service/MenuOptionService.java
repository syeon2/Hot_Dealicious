package project.hotdealicious.menu.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.dao.IMenuOptionDAO;
import project.hotdealicious.menu.dto.SaveMenuOptionDto;
import project.hotdealicious.menu.dto.UpdateMenuOptionDto;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

	private final IMenuOptionDAO menuOptionDAO;

	public Long registerMenuOption(SaveMenuOptionDto saveMenuOptionDto) {
		return menuOptionDAO.save(saveMenuOptionDto);
	}

	public void updateMenuOption(Long id, UpdateMenuOptionDto updateMenuOptionDto) {
		updateMenuOptionDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		menuOptionDAO.update(id, updateMenuOptionDto);
	}

	public void removeMenuOption(Long id) {
		menuOptionDAO.delete(id);
	}
}
