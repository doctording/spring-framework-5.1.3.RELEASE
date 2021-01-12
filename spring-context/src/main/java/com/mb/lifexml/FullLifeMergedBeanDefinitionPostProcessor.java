package com.mb.lifexml;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @Author mubi
 * @Date 2020/11/27 10:11
 */
public class FullLifeMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

	public FullLifeMergedBeanDefinitionPostProcessor(){
		System.out.println("FullLifeMergedBeanDefinitionPostProcessor ...constructor");
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanName.equals("fullBeanB")) {
			beanDefinition.getPropertyValues().add("name","xxx");
		}
	}
}
