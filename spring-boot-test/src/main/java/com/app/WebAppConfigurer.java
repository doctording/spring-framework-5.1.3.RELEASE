package com.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author mubi
 * @Date 2020/12/5 17:44
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 可添加多个
		registry.addInterceptor(new TestFilter()).addPathPatterns("/**");
	}
}
