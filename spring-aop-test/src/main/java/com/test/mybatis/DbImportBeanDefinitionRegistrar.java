package com.test.mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 * ImportBeanDefinitionRegistrar 动态的注册自己写的Bean, 这里是`DbMapperFactoryBean`
 */
public class DbImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DbMapperFactoryBean.class);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		// 这里可以利用反射，for循环mapper package下的所有Mapper类，然后添加所有
		beanDefinition.getConstructorArgumentValues()
			.addGenericArgumentValue("com.test.mapper.UserMapper");
		registry.registerBeanDefinition("dbMapperFactoryBean", beanDefinition);
	}
}
