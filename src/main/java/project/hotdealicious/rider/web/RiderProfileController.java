package project.hotdealicious.rider.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.rider.domain.WorkStatus;
import project.hotdealicious.rider.dto.RiderGeoDto;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;
import project.hotdealicious.rider.service.RiderProfileService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderProfileController {

	private final RiderProfileService riderProfileService;
	private final RedisTemplate<String, Object> redisGeoTemplate;
	private final RedisTemplate<String, String> redisRoutingTableTemplate;

	@PostMapping
	public ApiResult<Long> join(@Valid @RequestBody SaveRiderDto saveRiderDto) {
		Long riderId = riderProfileService.join(saveRiderDto);
		return ApiResult.onSuccess(riderId);
	}

	@PostMapping("/{id}")
	public ApiResult<Long> updateRiderInfo(@PathVariable Long id, @Valid @RequestBody UpdateRiderDto updateRiderDto) {
		Long riderId = riderProfileService.update(id, updateRiderDto);
		return ApiResult.onSuccess(riderId);
	}

	@DeleteMapping("/{id}")
	public ApiResult<Long> withdrawRider(@PathVariable Long id) {
		Long riderId = riderProfileService.withdraw(id);
		return ApiResult.onSuccess(riderId);
	}

	@MessageMapping("/commute")
	public ApiResult<Long> checkCommute(RiderGeoDto riderGeoDto, HttpServletRequest request) {
		Point point = new Point(riderGeoDto.getData().getX(), riderGeoDto.getData().getY());
		redisGeoTemplate.opsForGeo().add("myMap", point, riderGeoDto.getRiderId());

		if (riderGeoDto.getWorkStatus() == WorkStatus.INIT) {
			connectWebSocketServerToRider(riderGeoDto.getRiderId(), request);
		}

		return ApiResult.onSuccess(riderGeoDto.getRiderId());
	}

	private void connectWebSocketServerToRider(@PathVariable Long id, HttpServletRequest request) {
		redisRoutingTableTemplate.opsForValue().set(String.valueOf(id), makeUri(request));
	}

	private String makeUri(HttpServletRequest request) {
		String scheme = request.getScheme();
		String host = request.getServerName();
		int remotePort = request.getServerPort();

		return scheme.concat("://").concat(host).concat(":").concat(String.valueOf(remotePort));
	}
}
