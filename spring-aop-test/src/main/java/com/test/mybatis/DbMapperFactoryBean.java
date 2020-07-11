package com.test.mybatis;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 * FactoryBean是一个特殊bean: 自己 + 产生的bean
 * Spring能获取到该bean, 和该bean产生的bean
 */
public class DbMapperFactoryBean implements FactoryBean<Object> {

	Class<?> mapperInterface;

	@Override
	public Object getObject() throws Exception {
		Object object = DbSession.getMapper(mapperInterface);
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
