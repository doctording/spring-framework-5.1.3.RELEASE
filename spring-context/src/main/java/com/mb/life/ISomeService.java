package com.mb.life;

import org.springframework.stereotype.Service;

/**
 * @Author mubi
 * @Date 2020/11/20 10:55
 */
@Service
public class ISomeService implements BaseService{
	@Override
	public String doSomething() {
		return "Hello AlanShelby"; // 增强效果：返回内容全部大写
	}

	@Override
	public String eat() {
		return "eat food";
	}
}
