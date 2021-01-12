package com.mb.service;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * 自定义一个默认的 BeanName 生成器
 * eg: AnnotationBeanNameGenerator
 *
 * @Author mubi
 * @Date 2020/11/3 19:09
 */
@Component
public class MyBeanNameGenerator implements BeanNameGenerator {
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String beanClassName = definition.getBeanClassName();
		String shortClassName = ClassUtils.getShortName(beanClassName);
		// 调用java.beans.Introspector的方法  首字母小写
		String decapitalName = Introspector.decapitalize(shortClassName);
		// 自定一规则去掉Imp字符
		if (decapitalName.contains("imp")) {
			decapitalName = decapitalName.replace("imp", "");
		}
		if (decapitalName.contains("Imp")) {
			decapitalName = decapitalName.replace("Imp", "");
		}
		System.out.println("MyBeanNameGenerator, beanClassName:" + beanClassName +
				" decapitalName:" + decapitalName);
		return decapitalName;
	}

}
