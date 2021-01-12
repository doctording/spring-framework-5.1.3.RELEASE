package com.mb.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
	public Cat() {
		System.out.println("Cat ...constructor");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// do some initialization work
		System.out.println("Cat ...afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Cat ...destroy");
	}

}
