package com.test.componentbean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author mubi
 * @Date 2020/7/16 23:52
 */
@Configuration
@ComponentScan("com.test.componentbean")
public class UserConfig {
	@Value("${key1}")
	String userName;

	@Bean
	public UserManager userManager() {
		UserManager userManager = new UserManager(userName);
		return userManager;
	}
}