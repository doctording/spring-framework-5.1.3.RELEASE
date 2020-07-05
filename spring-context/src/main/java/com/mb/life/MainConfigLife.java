package com.mb.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 */
@ComponentScan("com.mb.life")
@Configuration
public class MainConfigLife {

//	@Scope("prototype")
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public Car car(){
		return new Car();
	}
}
