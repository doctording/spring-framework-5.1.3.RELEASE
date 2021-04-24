package com.bfpp;

import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/6/10 22:19
 */
@Component
public class B {

	public B() {
		System.out.println("B()");
	}

	public String getIp() {
		EnvService envService = SpringContextUtils.getBean("envService");
		return envService.getIp() + "B";
	}
}
