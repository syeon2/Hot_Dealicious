package project.hotdealicious.customer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerLoginDto {

	@Email(message = "이메일의 양식을 지켜주세요.(ex. xxxx@xxxx.com")
	@NotBlank(message = "이메일을 입력해주세요.")
	private final String email;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private final String password;
}
