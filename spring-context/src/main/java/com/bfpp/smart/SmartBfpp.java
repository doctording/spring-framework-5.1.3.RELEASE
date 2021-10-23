package com.bfpp.smart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * @Author mubi
 * @Date 2021/10/23 13:12
 */
public class SmartBfpp implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		if(beanName.equals("user")) {
			System.out.println("SmartBfpp predictBeanType");
			return beanClass;
		}
		return null;
	}

	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		if(beanName.equals("user")) {
			System.out.println("SmartBfpp determineCandidateConstructors");
			Constructor<?>[] constructors = beanClass.getConstructors();
			return constructors;
		}
		return null;
	}

}
