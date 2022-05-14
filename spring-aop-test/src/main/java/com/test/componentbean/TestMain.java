package com.test.componentbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author mubi
 * @Date 2022/5/14 19:19
 */
public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(UserConfig.class);
		UserManager userManager = ac.getBean(UserManager.class);
		System.out.println(userManager);
	}
}
