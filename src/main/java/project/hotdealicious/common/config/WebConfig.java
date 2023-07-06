package project.hotdealicious.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import project.hotdealicious.common.aop.CustomerLoginCheckInterceptor;
import project.hotdealicious.common.aop.OwnerLoginCheckInterceptor;
import project.hotdealicious.common.aop.RiderLoginCheckInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer {

	// TODO: 남은 API 구현 후 addPath, Exclude Path 설정 예정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CustomerLoginCheckInterceptor())
			.order(1);

		registry.addInterceptor(new OwnerLoginCheckInterceptor())
			.order(2);

		registry.addInterceptor(new RiderLoginCheckInterceptor())
			.order(3);
	}
}
