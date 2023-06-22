package project.hotdealicious.rider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RiderLoginDto {

	@NotNull
	private Long id;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
}
