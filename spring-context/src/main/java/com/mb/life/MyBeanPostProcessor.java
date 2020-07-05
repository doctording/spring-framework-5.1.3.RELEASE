package com.mb.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor ...constructor");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization:" + beanName + " " + bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization:" + beanName + " " + bean);
		return bean;
	}
}
