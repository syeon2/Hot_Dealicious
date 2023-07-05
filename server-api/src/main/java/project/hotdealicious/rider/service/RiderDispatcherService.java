package project.hotdealicious.rider.service;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.config.redis.RedisWaitOrderConfig;
import project.hotdealicious.order.domain.OrderStatus;
import project.hotdealicious.order.dto.UpdateOrdersDto;
import project.hotdealicious.order.service.OrdersService;
import project.hotdealicious.rider.dto.GeoLocation;

@Service
@RequiredArgsConstructor
public class RiderDispatcherService {

	private final OrdersService ordersService;
	private final RedisTemplate<String, String> redisWaitOrderTemplate;

	public void dispatchOrder(Long id, GeoLocation geoLocation) {
		GeoResults<RedisGeoCommands.GeoLocation<String>> currentLocation = redisWaitOrderTemplate.opsForGeo()
			.radius(RedisWaitOrderConfig.WAIT_ORDER,
				new Circle(new Point(geoLocation.getX(), geoLocation.getY()), new Distance(5, Metrics.KILOMETERS)));

		if (currentLocation == null) {
			throw new NoSuchElementException("잘못된 키입력입니다.");
		} else {
			if (currentLocation.getContent().isEmpty()) {
				// TODO:: 주변에 매장이 없을 경우 예외 처리
				throw new NoSuchElementException("주변에 매칭될 매장이 없습니다.");
			}

			RedisGeoCommands.GeoLocation<String> nearestLocation = currentLocation.getContent().get(0).getContent();
			Long orderId = Long.parseLong(nearestLocation.getName());

			UpdateOrdersDto updateOrdersDto = new UpdateOrdersDto(OrderStatus.PROGRESS);
			updateOrdersDto.setRiderId(id);
			updateOrdersDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			ordersService.changeOrderStatus(orderId, updateOrdersDto);
		}
	}
}
