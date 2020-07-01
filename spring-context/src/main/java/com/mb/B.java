package com.mb;


/**
 * @Author mubi
 * @Date 2020/6/20 12:15
 */
public class B {

	private A a;

	public B() {
		System.out.println("constructor B()");
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
