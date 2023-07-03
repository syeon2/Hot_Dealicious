package project.hotdealicious.menu.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.dto.SaveMenuOptionDto;
import project.hotdealicious.menu.dto.UpdateMenuOptionDto;
import project.hotdealicious.menu.service.MenuOptionService;

@RestController
@RequestMapping("/api/v1/menu/option")
@RequiredArgsConstructor
public class MenuOptionController {

	private final MenuOptionService menuOptionService;

	@PostMapping
	public Long registerMenuOption(@Valid @RequestBody SaveMenuOptionDto saveMenuOptionDto) {
		return menuOptionService.registerMenuOption(saveMenuOptionDto);
	}

	@PostMapping("/{id}")
	public void updateMenuOption(@PathVariable Long id, @Valid @RequestBody UpdateMenuOptionDto updateMenuOptionDto) {
		menuOptionService.updateMenuOption(id, updateMenuOptionDto);
	}

	@DeleteMapping("/{id}")
	public void removeMenuOption(@PathVariable Long id) {
		menuOptionService.removeMenuOption(id);
	}
}
