package com.mb.dependencyaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/21 15:46
 */
@Component
public class M {
	@Autowired
	Z z;//注入z

	//构造方法
	public M(){
		System.out.println("M create");
	}

	public Z getZ(){
		return z;
	}
}
