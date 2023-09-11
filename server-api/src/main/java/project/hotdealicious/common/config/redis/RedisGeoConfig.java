package project.hotdealicious.common.config.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisGeoConfig {

	@Value("${spring.redis.geo.host}")
	private String host;

	@Value("${spring.redis.geo.port}")
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
