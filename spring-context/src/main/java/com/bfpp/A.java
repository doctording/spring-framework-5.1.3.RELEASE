package com.bfpp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/6/10 22:19
 */
@Component
public class A implements InitializingBean{

	private String ip;

	@Autowired
	EnvService envService;

	A(){
		System.out.println("A()");
	}

	public String getIp() {
		return this.ip;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.ip = envService.getIp() + "A";
	}
}
