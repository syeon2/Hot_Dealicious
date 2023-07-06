package project.hotdealicious.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisRoutingTableConfig {

	/**
	 * Redis Routing Table은 특정 WebSocket 서버와 라이더 클라이언트 간의 연결을 지속시키기 위해 사용한다.
	 * Redis Routing Table은 별개의 서버에서 webSocket 클라이언트로부터 요청받아 URI가 있으면 바로 다이렉트로
	 * WebSocket 서버로 요청할 것이고, 만약 URI가 없다면 HA Proxy같은 로드벨런서를 통해 적절한 WebSocket 서버와 연결한 후 요청할 것이다.
	 *
	 * Redis Routing Table도 서버 어플리케이션과는 별개의 서버에서 구동하여 클라이언트로부터 요청을 받아야하지만, 현재 PC가 하나뿐이므로 port 번호를
	 * 변경하는 것으로 마무리한다.
	 */

	@Value("${spring.redis.host}")
	private String host;

	private final Integer port = 6370;

	@Bean
	public RedisConnectionFactory redisRoutingTableConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	@Bean
	@Qualifier("redisRoutingTableTemplate")
	public RedisTemplate<String, String> redisRoutingTableTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisRoutingTableConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());

		return redisTemplate;
	}
}
