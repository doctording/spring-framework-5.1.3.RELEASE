package com.app;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author mubi
 * @Date 2020/7/26 12:21
 */
@Configuration
@ComponentScan("com")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

	// converters 使用 @EnableWebMvc 会 加入到 ServletContext
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter =
				new FastJsonHttpMessageConverter();
		converters.add(fastJsonHttpMessageConverter);
	}
}
