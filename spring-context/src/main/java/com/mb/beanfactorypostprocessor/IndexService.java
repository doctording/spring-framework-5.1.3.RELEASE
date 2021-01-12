package com.mb.beanfactorypostprocessor;

import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/11/7 20:22
 */
@Component
public class IndexService {
	public int func(int a){
		return a + 100;
	}

	public void des(){
		System.out.println("destroy IndexService");
	}
}
