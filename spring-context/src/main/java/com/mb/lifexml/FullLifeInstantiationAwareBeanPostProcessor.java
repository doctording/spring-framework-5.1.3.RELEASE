package com.mb.lifexml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
public class FullLifeInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	public FullLifeInstantiationAwareBeanPostProcessor() {
		System.out.println("FullLifeInstantiationAwareBeanPostProcessor ...constructor");
	}

	/**
	 * BeanPostProcessor接口中的方法
	 * 在Bean的自定义初始化方法之前执行
	 * Bean对象已经存在了
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(">>postProcessBeforeInitialization" + ":" + beanName);
		return bean;
	}

	/**
	 * BeanPostProcessor接口中的方法
	 * 在Bean的自定义初始化方法执行完成之后执行
	 * Bean对象已经存在了
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("<<postProcessAfterInitialization" + ":" + beanName);
		return bean;
	}

	/**
	 * InstantiationAwareBeanPostProcessor中自定义的方法
	 * 在方法实例化之前执行  Bean对象还没有
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("--->postProcessBeforeInstantiation" + ":" + beanName);
		return null;
	}

	/**
	 * InstantiationAwareBeanPostProcessor中自定义的方法
	 * 在方法实例化之后执行 Bean对象已经创建出来了
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("<---postProcessAfterInstantiation" + ":" + beanName);
		return true;
	}

}
