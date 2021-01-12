package com.mb.dependency;


/**
 * @Author mubi
 * @Date 2020/6/20 12:15
 */
public class WeakA {
	public WeakA() {
		System.out.println("WeakA created");
		System.out.println(D.name);
	}
}
