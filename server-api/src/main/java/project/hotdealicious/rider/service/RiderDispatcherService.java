package project.hotdealicious.rider.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
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
		GeoResults<RedisGeoCommands.GeoLocation<String>> currentLocation = getOrderedStoreAroundRider(geoLocation);

		if (currentLocation == null) {
			throw new NoSuchElementException("잘못된 키입력입니다.");
		} else {
			if (currentLocation.getContent().isEmpty()) {
				throw new NoSuchElementException("주변에 매칭될 매장이 없습니다.");
			}

			findNearestStoreAndDispatchRider(id, currentLocation);
		}
	}

	/**
	 * orderId를 key값으로 주문해야하는 위치를 기준으로 Geo 데이터가 저장됩니다.
	 * 동시적으로 라이더가 같은 주문 Id을 대상으로 배차받는 동시성 이슈를 해결하기 위해 Redis를 사용하여 분산락을 구현하였습니다.
	 * Geo 데이터를 저장하고 있는 Redis에서 주문해야하는 아이디를 분산락을 통해 lock이 걸려있는지 확인 이후 lock이 있다면 다른 order Id를 배차 받고
	 * lock이 없다면 라이더가 그대로 orderId에 맞는 주문을 배달하게 됩니다.
	 */
	private void findNearestStoreAndDispatchRider(Long id,
		GeoResults<RedisGeoCommands.GeoLocation<String>> currentLocation) {
		List<GeoResult<RedisGeoCommands.GeoLocation<String>>> nearestStoreList = currentLocation.getContent();

		for (GeoResult<RedisGeoCommands.GeoLocation<String>> store : nearestStoreList) {
			String orderId = store.getContent().getName();

			Boolean isLock = redisWaitOrderTemplate.opsForValue()
				.setIfAbsent(orderId, "lock", Duration.ofMillis(1000));

			if (Boolean.TRUE.equals(isLock)) {
				UpdateOrdersDto updateOrdersDto = new UpdateOrdersDto(OrderStatus.PROGRESS);
				updateOrdersDto.setRiderId(id);
				updateOrdersDto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				ordersService.changeOrderStatus(Long.parseLong(orderId), updateOrdersDto);
				return;
			}
		}

		throw new NoSuchElementException("주변에 매칭될 매장이 없습니다.");
	}

	private GeoResults<RedisGeoCommands.GeoLocation<String>> getOrderedStoreAroundRider(GeoLocation geoLocation) {
		return redisWaitOrderTemplate.opsForGeo()
			.radius(RedisWaitOrderConfig.WAIT_ORDER,
				new Circle(new Point(geoLocation.getX(), geoLocation.getY()), new Distance(5, Metrics.KILOMETERS)));
	}
}
