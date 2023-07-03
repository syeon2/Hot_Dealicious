package project.hotdealicious.owner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveOwnerDto {

	private Long id;

	@NotBlank(message = "비밀번호를 작성해주세요.")
	private String password;

	@NotBlank(message = "이름을 작성해주세요.")
	private final String name;

	@NotBlank(message = "전화번호를 작성해주세요")
	@Pattern(regexp = "[0-9]{10,11}", message = "10 ~ 11자리의 숫자만 입력 가능합니다.")
	private final String phone;

	private String salt;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
