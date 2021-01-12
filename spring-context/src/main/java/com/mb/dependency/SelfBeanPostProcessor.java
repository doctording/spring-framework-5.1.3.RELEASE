package com.mb.dependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author mubi
 * @Date 2020/6/30 08:59
 */
public class SelfBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equalsIgnoreCase("A")) {
			// AOP：即生成一个代理对象
			System.out.println("A postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equalsIgnoreCase("A")) {
			// AOP：即生成一个代理对象
			System.out.println("A postProcessAfterInitialization");
		}
		return bean;
	}
}
