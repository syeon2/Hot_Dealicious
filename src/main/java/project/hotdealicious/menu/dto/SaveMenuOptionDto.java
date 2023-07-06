package project.hotdealicious.menu.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveMenuOptionDto {

	private Long id;

	@NotBlank(message = "이름을 입력해주세요.")
	private final String name;

	@NotNull
	private final String introduction;

	@Min(value = 0, message = "0이하는 입력할 수 없습니다.")
	private final BigDecimal price;

	@NotNull
	private final Long menuId;
}
