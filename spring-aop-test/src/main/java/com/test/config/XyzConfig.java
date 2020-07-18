package com.test.config;

import com.test.entity.X;
import com.test.entity.Y;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author mubi
 * @Date 2020/7/8 22:38
 */
@Configuration
public class XyzConfig {

	public XyzConfig(){
		System.out.println("init XyzConfig");
	}

	@Bean
	public X x(){
		return new X();
	}

	@Bean
	public Y y(){
		x();
		return new Y();
	}
}
