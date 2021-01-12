package com.mb.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 * ComponentScan 自定义一个nameGenerator 实现 byName 方式的bean扫描
 */
@ComponentScan(basePackages = "com.mb.service", nameGenerator = MyBeanNameGenerator.class)
@Configuration
public class ServiceConfig {

	/**
	 * TransferService 需要依赖 AccountRepository 才能创建出来
	 * we can materialize that dependency with a method parameter（通过方法参数来实现这种依赖）
	 */
	@Bean
	public TransferService transferService(AccountRepository accountRepository) {
		return new TransferServiceImpl(accountRepository);
	}

	@Bean
	public AccountRepository accountRepository(){
		return new AccountRepository();
	}
}
