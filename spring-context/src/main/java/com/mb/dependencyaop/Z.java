package com.mb.dependencyaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author mubi
 * @Date 2020/11/21 15:46
 */
@Component
public class Z implements ApplicationContextAware {
	@Autowired
	M m;//注入m

	//构造方法
	public Z(){
		System.out.println("Z create");
	}

	public M getM(){
		return m;
	}

	//生命周期初始化回调方法
	@PostConstruct
	public void zinit(){
		System.out.println("call z lifecycle init callback");
	}

	//ApplicationContextAware 回调方法
	@Override
	public void setApplicationContext(ApplicationContext ac) {
		System.out.println("z call aware callback");
	}

	public void testAA(){
		System.out.println(m);
	}
}
