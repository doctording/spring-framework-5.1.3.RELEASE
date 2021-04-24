package com.bfpp;

import com.bfpp.impl.EnvServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 15:42
 */
@Component
public class BfPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 手动注册单例 bean
		EnvService envService = new EnvServiceImpl();
		beanFactory.registerSingleton("envService", envService);
		// 改变 BeanDefinition
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("a");
		beanDefinition.setDependsOn("b");
		System.out.println("registerSingleton envService");
	}
}
