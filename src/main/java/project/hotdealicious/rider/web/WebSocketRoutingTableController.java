package project.hotdealicious.rider.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;

/**
 * Redis Routing Table이 있는 서버에서 실행되는 API
 * 메인 서버 어플리케이션에서 실행되지 않는다. (단, 여기에서는 프로젝트이기 때문에 하나의 서버로 동작한다.)
 */

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class WebSocketRoutingTableController {

	private final RedisTemplate<String, String> redisRoutingTableTemplate;

	@GetMapping("/{id}")
	public ApiResult<String> getConnectedWebSocketUri(@PathVariable String id, HttpServletRequest request) {
		String str = redisRoutingTableTemplate.opsForValue().get(id);

		return ApiResult.onSuccess(str);
	}
}
