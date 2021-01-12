package com.mb.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 */
@Component
public class SelfBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	public SelfBeanDefinitionRegistryPostProcessor(){
		System.out.println("===new SelfBeanDefinitionRegistryPostProcessor()");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// new一个 OtherService 的beanDefinition对象，方便测试动态添加
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(Other2Service.class);
		// 主动注册一个beanDefinition对象
		registry.registerBeanDefinition("other2Service", beanDefinition);
	}
}
