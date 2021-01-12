package com.mb.beanfactorypostprocessor;

/**
 * 注意这个类没有加任何注解，都不是个bean, 理论上不归Spring进行处理
 *
 * @Author mubi
 * @Date 2020/11/7 20:22
 */
public class OtherService {

	public OtherService(){
		System.out.println("new OtherService()");
	}
}
