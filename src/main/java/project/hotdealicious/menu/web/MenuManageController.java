package project.hotdealicious.menu.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.dto.MenuDetailDto;
import project.hotdealicious.menu.dto.SaveMenuDto;
import project.hotdealicious.menu.dto.UpdateMenuDto;
import project.hotdealicious.menu.service.MenuManageService;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuManageController {

	private final MenuManageService menuService;

	@PostMapping
	public Long registerMenu(@Valid @RequestBody SaveMenuDto saveMenuDto) {
		return menuService.registerMenu(saveMenuDto);
	}

	@GetMapping("/{id}")
	public MenuDetailDto findMenuDetail(@PathVariable Long id) {
		return menuService.findMenuDetail(id);
	}

	@GetMapping("/shop/{shopId}")
	public List<Menu> findMenusInShop(@PathVariable Long shopId) {
		return menuService.findMenusInShop(shopId);
	}

	@PostMapping("/{id}")
	public void updateMenu(@PathVariable Long id, @Valid @RequestBody UpdateMenuDto updateMenuDto) {
		menuService.updateMenu(id, updateMenuDto);
	}

	@DeleteMapping("/{id}")
	public void withdrawMenu(@PathVariable Long id) {
		menuService.withdraw(id);
	}
}
