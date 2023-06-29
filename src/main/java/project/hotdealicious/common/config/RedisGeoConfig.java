package project.hotdealicious.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisGeoConfig {

	/**
	 * Geo Data는 라이더 클라이언트로부터 WebSocket 서버에 지속적으로 전송되는 데이터이다.
	 * Redis Routing Table을 사용하여 분산 환경에서도 특정 WebSocket 서버와 라이더가 연결될 수 있는 환경이다.
	 * In-Memory 특징을 살려 라이더 클라이언트로부터 Geo 데이터를 전송받으면 별도의 네트워크 처리 없이 해당 WebSocket 서버에 메모리 저장소로
	 * 저장하는 것이 좋다고 생각되어 서버와 함께 실행된다.
	 */

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Bean
	public RedisConnectionFactory redisGeoConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	@Bean
	@Qualifier("redisGeoTemplate")
	public RedisTemplate<String, Object> redisGeoTemplate() {
		RedisTemplate<String, Object> redisGeoTemplate = new RedisTemplate<>();
		redisGeoTemplate.setConnectionFactory(redisGeoConnectionFactory());
		redisGeoTemplate.setKeySerializer(new StringRedisSerializer());
		redisGeoTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return redisGeoTemplate;
	}
}
