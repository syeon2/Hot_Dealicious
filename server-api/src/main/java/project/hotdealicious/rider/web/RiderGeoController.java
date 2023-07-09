package project.hotdealicious.rider.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.rider.domain.WorkStatus;
import project.hotdealicious.rider.dto.RiderGeoDto;
import project.hotdealicious.rider.service.RiderProfileService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderGeoController {

	private static final String WEBSOCKET_SCHEMA = "ws";
	public static final String RIDER_LOCATION = "RIDER_LOCATION";

	private final RiderProfileService riderProfileService;
	private final RedisTemplate<String, Object> redisGeoTemplate;
	private final RedisTemplate<String, String> redisRoutingTableTemplate;
	private final SimpMessageSendingOperations messageSendingOperations;
	private final Environment environment;

	@PostMapping("/work")
	public ApiResult<Long> checkWorkStatus(@RequestBody RiderGeoDto riderGeoDto, HttpServletRequest request) {
		riderProfileService.updateWorkStatus(riderGeoDto.getChannelId(), riderGeoDto.getWorkStatus());

		connectWebSocketServerToRider(riderGeoDto, request);

		return ApiResult.onSuccess(riderGeoDto.getChannelId());
	}

	@MessageMapping("/location")
	public ApiResult<Long> updateLocation(RiderGeoDto riderGeoDto) {
		Point point = new Point(riderGeoDto.getData().getX(), riderGeoDto.getData().getY());
		redisGeoTemplate.opsForGeo().add(RIDER_LOCATION, point, riderGeoDto.getChannelId());
		messageSendingOperations.convertAndSend("/sub/channel" + riderGeoDto.getChannelId(), riderGeoDto);

		return ApiResult.onSuccess(riderGeoDto.getChannelId());
	}

	private void connectWebSocketServerToRider(RiderGeoDto riderGeoDto, HttpServletRequest request) {
		if (riderGeoDto.getWorkStatus() == WorkStatus.WAIT) {
			redisRoutingTableTemplate.opsForValue().set(String.valueOf(riderGeoDto.getChannelId()), makeUri(request));
		} else if (riderGeoDto.getWorkStatus() == WorkStatus.OFFLINE) {
			redisGeoTemplate.opsForGeo().remove(RIDER_LOCATION, riderGeoDto.getChannelId());
		} else {
			throw new IllegalArgumentException("잘못된 업무 상태입니다.");
		}
	}

	private String makeUri(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		String ip = request.getLocalAddr();
		Integer port = request.getLocalPort();

		return sb.append(WEBSOCKET_SCHEMA).append("://").append(ip).append(":").append(port).toString();
	}
}
