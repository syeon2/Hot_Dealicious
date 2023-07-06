package project.hotdealicious.rider.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateRiderDto {

	@NotBlank(message = "비밀번호를 작성해주세요.")
	private String password;

	@NotBlank(message = "전화번호를 작성해주세요.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10 ~ 11자리의 숫자만 입력 가능합니다.")
	private final String phone;

	private Timestamp updatedAt;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
