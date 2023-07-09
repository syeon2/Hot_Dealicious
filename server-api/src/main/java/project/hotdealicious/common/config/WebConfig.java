package project.hotdealicious.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

	// TODO: 남은 API 구현 후 addPath, Exclude Path 설정 예정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new LoginCheckInterceptor())
		// 	.order(1);
	}
}
