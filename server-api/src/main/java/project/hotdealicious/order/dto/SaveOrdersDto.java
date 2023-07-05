package project.hotdealicious.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.hotdealicious.rider.domain.WorkStatus;

@Getter
@RequiredArgsConstructor
public class SaveOrdersDto {

	private Long id;

	@NotBlank(message = "업무 상태를 입력해주세요.")
	private WorkStatus workStatus;

	@NotBlank(message = "주소를 입력해주세요.")
	private String address;

	@NotNull(message = "소비자 아이디를 입력해주세요.")
	private Long customerId;

	@NotNull(message = "매장 아이디를 입력해주세요.")
	private Long storeId;
}
