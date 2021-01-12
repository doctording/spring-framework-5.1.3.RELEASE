package com.mb.lifexml;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
public class FullBeanB implements InitializingBean, DisposableBean {

	private String name;

	public FullBeanB() {
		System.out.println("FullBeanB ...constructor");
	}

	public FullBeanB(String name) {
		this.name = name;
		System.out.println("FullBeanB ...constructor(String name)");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("FullBeanB ...InitializingBean afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("FullBeanB ...DisposableBean destroy");
	}

	public void selfInit() {
		System.out.println("FullBeanB ...selfInit");
	}

	public void selfDestroy() {
		System.out.println("FullBeanB ...selfDestroy");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
