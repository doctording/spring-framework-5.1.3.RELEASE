package com.mb.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 */
@Component
public class SelfBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public SelfBeanFactoryPostProcessor(){
		System.out.println("new SelfBeanFactoryPostProcessor()");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("indexService");
		beanDefinition.setDestroyMethodName("des");

		//转换为子类，因为父类没有添加beanDefinition对象的api
		DefaultListableBeanFactory defaultBf = (DefaultListableBeanFactory) beanFactory;
		// new一个 OtherService 的beanDefinition对象，方便测试动态添加
		GenericBeanDefinition otherBean = new GenericBeanDefinition();
		otherBean.setBeanClass(OtherService.class);
		//添加一个beanDefinition对象，原本这个 OtherService 没有被spring扫描到
		defaultBf.registerBeanDefinition("otherService", otherBean);
	}
}
