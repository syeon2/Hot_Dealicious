package project.hotdealicious.order.service;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.order.dao.IOrdersDao;
import project.hotdealicious.order.domain.OrderStatus;
import project.hotdealicious.order.dto.SaveOrdersDto;
import project.hotdealicious.order.dto.UpdateOrdersDto;

@Service
@RequiredArgsConstructor
public class OrdersService {

	public static final String WAIT_ORDER = "WAIT_ORDER";

	private final IOrdersDao ordersDao;
	private final RedisTemplate<String, Object> redisWaitOrderTemplate;

	public Long createOrder(SaveOrdersDto saveOrdersDto) {
		// TODO:: 주문 재고 처리 및 결제 정보 업데이트
		return ordersDao.save(saveOrdersDto);
	}

	public void changeOrderStatus(Long id, UpdateOrdersDto updateOrdersDto) {
		if (endOrder(updateOrdersDto)) {
			redisWaitOrderTemplate.opsForGeo().remove(WAIT_ORDER, id);
		} else if (updateOrdersDto.getOrderStatus() == OrderStatus.SEARCH_RIDER) {
			// TODO:: 임시 위도 경도 값 설정 -> 추후 외부 주소값 요청 API 붙이는 작업
			Point point = new Point(128.123, 24.214);

			redisWaitOrderTemplate.opsForGeo().add(WAIT_ORDER, point, id);
		} else if (isNotOrdersStatus(updateOrdersDto)) {
			throw new IllegalArgumentException("잘못된 주문 상태 요청입니다.");
		}

		ordersDao.update(id, updateOrdersDto);
	}

	private boolean isNotOrdersStatus(UpdateOrdersDto updateOrdersDto) {
		return updateOrdersDto.getOrderStatus() != OrderStatus.PROGRESS
			&& updateOrdersDto.getOrderStatus() != OrderStatus.WAIT;
	}

	private boolean endOrder(UpdateOrdersDto updateOrdersDto) {
		return updateOrdersDto.getOrderStatus() == OrderStatus.COMPLETE
			|| updateOrdersDto.getOrderStatus() == OrderStatus.CANCEL;
	}
}
