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
		System.out.println("X new");
		return new X();
	}

	/**
	 * 加上@Configuration 有了代理, y()方法已经不是y()方法了, XyzConfig被CGLIB代理了
	 */
	@Bean
	public Y y(){
		x();
		return new Y();
	}
}
