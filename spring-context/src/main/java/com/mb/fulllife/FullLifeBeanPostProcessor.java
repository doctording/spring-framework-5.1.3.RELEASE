package com.mb.fulllife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
public class FullLifeBeanPostProcessor implements BeanPostProcessor {
	public FullLifeBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor ...constructor");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization:" + beanName + ":" + bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization:" + beanName + ":" + bean);
		return bean;
	}
}
