package com.mb.autowired;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author mubi
 * @Date 2020/11/18 10:51
 */
public class Ca {

	/**
	 * Autowired 是手动注入
	 */
	@Autowired
	Cb cb;

	/**
	 * 代码能运行起来，A能注入B
	 * 但是在xml配置文件中并没有去手动维护、描述他们之间的依赖关系，
	 * 而是在xml的根标签上面写了一行default-autowire="byType"
	 * 且set方法名子也无所谓，只要里面的type是个bean: cb
	 */
	public void setXxx(Cb cb) {
		System.out.println("这个set方法如果配置了自动装配且是byType方式是可以的,byName不行:" + cb);
	}

	public void setCc(Cc cc) {
		System.out.println("cc byName, byType都可以:" + cc);
	}

	public void cbOut(){
		System.out.println("Cb:" + cb);
	}
}
