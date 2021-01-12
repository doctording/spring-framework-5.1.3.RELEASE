package com.mb.autowired;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Author mubi
 * @Date 2020/11/18 11:07
 */
public class ModeBeanFactoryPostprocessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition ca = (GenericBeanDefinition)
				beanFactory.getBeanDefinition("ca");
		/**
		 * 打印 Ca 的注入模型
		 * {@link org.springframework.beans.factory.config.AutowireCapableBeanFactory}
		 * AUTOWIRE_NO = 0;
		 * AUTOWIRE_BY_NAME = 1;
		 * AUTOWIRE_BY_TYPE = 2;
		 * AUTOWIRE_CONSTRUCTOR = 3;
		 */
		System.out.println("ca autowiring mode=" + ca.getAutowireMode() + ":" + getAutowire(ca.getAutowireMode()));
	}

	private String getAutowire(int mode) {
		String[] arr = {
				"AUTOWIRE_NO", "AUTOWIRE_BY_NAME", "AUTOWIRE_BY_TYPE", "AUTOWIRE_CONSTRUCTOR"
		};
		return arr[mode];
	}
}
