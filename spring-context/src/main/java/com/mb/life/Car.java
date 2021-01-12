package com.mb.life;

import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 15:40
 */
@Component
public class Car {
	public Car() {
		System.out.println("car ...constructor");
	}

	/**
	 * 实例化并设置好所有属性后，默认的初始化工作的方法
	 * 默认的
	 */
	public void init() {
		System.out.println("Car ...init");
	}

	public void destroy() {
		System.out.println("Car ...destroy");
	}

}
