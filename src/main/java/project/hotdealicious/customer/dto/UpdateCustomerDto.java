package project.hotdealicious.customer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCustomerDto {

	private Long id;

	@NotBlank(message = "비밀번호를 작성해주세요.")
	private final String password;

	@NotBlank(message = "전화번호를 작성해주세요.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10 ~ 11자리의 숫자만 입력 가능합니다.")
	private final String phone;

	@NotBlank(message = "주소를 작성해주세요.")
	private final String address;
}
