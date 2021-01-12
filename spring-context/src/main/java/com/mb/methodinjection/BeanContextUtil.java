package com.mb.methodinjection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/4 17:36
 */
@Component
public class BeanContextUtil implements BeanFactoryAware {

	private static BeanFactory beanFactory;

	BeanContextUtil(){
		System.out.println("BeanContextUtil construct");
	}

	public static void setContextBean(BeanFactory  bf){
		beanFactory = bf;
	}

	public static Object getContextBean(String  beanName){
		return beanFactory.getBean(beanName);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		BeanContextUtil.setContextBean(beanFactory);
	}

	public static Object getBean(String beanName){
		return BeanContextUtil.getContextBean(beanName);

	}
}
