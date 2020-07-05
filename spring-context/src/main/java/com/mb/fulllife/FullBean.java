package com.mb.fulllife;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/4 16:12
 */
public class FullBean implements InitializingBean, DisposableBean {

	private FullBeanB fullBeanB;

	private String name;

	public FullBean() {
		System.out.println("FullBean ...constructor");
	}

	public FullBean(String name) {
		this.name = name;
		System.out.println("FullBean ...constructor(String name)");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("FullBean ...InitializingBean afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("FullBean ...DisposableBean destroy");
	}

	public void selfInit() {
		System.out.println("FullBean ...selfInit");
	}

	public void selfDestroy() {
		System.out.println("FullBean ...selfDestroy");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FullBeanB getFullBeanB() {
		return fullBeanB;
	}

	public void setFullBeanB(FullBeanB fullBeanB) {
		this.fullBeanB = fullBeanB;
	}
}
