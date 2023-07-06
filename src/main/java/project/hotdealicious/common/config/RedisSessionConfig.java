package project.hotdealicious.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

	/**
	 * Redis Session은 분산 환경에서 특정 회원의 로그인 관리를 하기 위해 사용했다.
	 * 그렇기 때문에 Redis Session은 서버 어플리케이션과는 별개의 서버에서 실행되어져야 한다.
	 * 지금은 하나의 PC에서 실행하기 때문에 포트번호만 다르지만, 이후 이 Redis Session을 실행하고 있는
	 * 별도의 host를 부여해야한다.
	 */

	@Value("${spring.redis.host}")
	private String host;

	private Integer port = 6371;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}
}
