package com.mb.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor ...constructor");
	}

	/**
	 * 初始化之前的自定义方法
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization: ..." + beanName + " ... " + bean);
		return bean;
	}

	/**
	 * 初始化之后的自定义方法
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization:" + beanName + " " + bean);
		if(beanName.equals(ISomeService.class.getSimpleName())){
			Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
					bean.getClass().getInterfaces(),
					new InvocationHandler() {
						/**
						 * @param proxy 代理监控对象
						 * @param method doSome()方法
						 * @param args doSome()方法执行时接收的实参
						 * @return
						 * @throws Throwable
						 */
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println("ISomeService 中的 doSome() 被拦截了···");
							String result = (String) method.invoke(bean, args);
							return result.toUpperCase();
						}
					});
			return proxy;
		}
		return bean;
	}
}
