package com.mb.life;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
@Component
public class Dog {
	public Dog() {
		System.out.println("Dog ...constructor");
	}

	/**
	 * @PostConstruct 让该方法成为bean的初始化方法
	 */
	@PostConstruct
	public void init(){
		System.out.println("Dog ...init");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Dog ...destroy");
	}

}
