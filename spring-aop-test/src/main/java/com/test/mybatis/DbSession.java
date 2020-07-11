package com.test.mybatis;

import java.lang.reflect.Proxy;

/**
 * @Author mubi
 * @Date 2020/7/11 13:45
 */
public class DbSession {

	public static Object getMapper(Class<?> clazz) {
		Class<?>[] clazzs = new Class<?>[]{clazz};
		Object object = Proxy.newProxyInstance(DbSession.class.getClassLoader(),
				clazzs,
				new DbInvocationHandler());
		return object;
	}
}
