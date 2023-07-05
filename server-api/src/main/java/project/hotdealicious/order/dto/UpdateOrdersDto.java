package project.hotdealicious.order.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.hotdealicious.order.domain.OrderStatus;

@Getter
@RequiredArgsConstructor
public class UpdateOrdersDto {

	@NotBlank(message = "주문 상태를 입력해주세요.")
	private final OrderStatus orderStatus;

	private Long riderId;
	private Timestamp updatedAt;

	public void setRiderId(Long riderId) {
		this.riderId = riderId;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
