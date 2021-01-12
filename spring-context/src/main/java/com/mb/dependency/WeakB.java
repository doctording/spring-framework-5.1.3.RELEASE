package com.mb.dependency;


/**
 * @Author mubi
 * @Date 2020/6/20 12:15
 */
public class WeakB {

	public WeakB() {
		System.out.println("WeakB created");
		D.name = "dName";
	}
}
