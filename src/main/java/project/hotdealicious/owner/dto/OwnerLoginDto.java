package project.hotdealicious.owner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OwnerLoginDto {

	@NotNull
	private final Long id;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private final String password;
}
