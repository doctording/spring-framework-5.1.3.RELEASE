package com.mb.dependency;

/**
 * @Author mubi
 * @Date 2020/6/20 12:15
 */
public class A {

	private B b;

	public A() {
		System.out.println("constructor A()");
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}