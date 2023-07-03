package project.websocketroutingtable.routingtable.web;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class WebSocketRoutingTableController {

	private final RedisTemplate<String, String> redisTemplate;

	@GetMapping("/{id}")
	public String getConnectedWebSocketUri(@PathVariable String id) {
		return redisTemplate.opsForValue().get(id);
	}
}
