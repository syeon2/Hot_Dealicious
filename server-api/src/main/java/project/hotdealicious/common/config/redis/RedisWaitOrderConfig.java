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
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisWaitOrderConfig {

	@Value("${spring.redis.wait_order.host}")
	private String host;

	@Value("${spring.redis.wait_order.port}")
	private Integer port;

	@Bean
	public RedisConnectionFactory redisWaitOrderFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	@Bean
	@Qualifier("redisWaitOrderTemplate")
	public RedisTemplate<String, Object> redisWaitOrderTemplate() {
		RedisTemplate<String, Object> redisGeoTemplate = new RedisTemplate<>();
		redisGeoTemplate.setConnectionFactory(redisWaitOrderFactory());
		redisGeoTemplate.setKeySerializer(new StringRedisSerializer());
		redisGeoTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return redisGeoTemplate;
	}
}