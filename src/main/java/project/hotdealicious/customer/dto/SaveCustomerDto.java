package project.hotdealicious.customer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveCustomerDto {

	private Long id;

	@Email(message = "이메일의 양식을 지켜주세요.(ex. xxxx@xxxx.com")
	@NotBlank(message = "이메일을 작성해주세요.")
	private final String email;

	@NotBlank(message = "비밀번호를 작성해주세요.")
	private String password;

	@NotBlank(message = "이름을 작성해주세요.")
	private final String name;

	@NotBlank(message = "전화번호를 작성해주세요.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10 ~ 11자리의 숫자만 입력 가능합니다.")
	private final String phone;

	@NotBlank(message = "주소를 작성해주세요.")
	private final String address;

	private String salt;

	public void setEncryptedPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
