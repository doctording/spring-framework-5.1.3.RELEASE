package com.mb.autowired;

/**
 * @Author mubi
 * @Date 2020/11/18 22:47
 */
public class IndexService {

	/**
	 * 构造函数这里能注入进来,why?
	 * @param beanService
	 */
	public IndexService(BeanService beanService) {
		System.out.println("beanService:" + beanService);
	}

}
