package project.hotdealicious.menu.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.hotdealicious.menu.domain.Menu;
import project.hotdealicious.menu.domain.MenuOption;

@Getter
@RequiredArgsConstructor
public class MenuDetailDto {

	private final Long id;
	private final String name;
	private final String introduction;
	private final Integer stock;
	private final BigDecimal price;

	private final List<MenuOption> menuOptions;

	public MenuDetailDto(Menu menu, List<MenuOption> menuOptions) {
		this.id = menu.getId();
		this.name = menu.getName();
		this.introduction = menu.getIntroduction();
		this.stock = menu.getStock();
		this.price = menu.getPrice();
		this.menuOptions = menuOptions;
	}
}
