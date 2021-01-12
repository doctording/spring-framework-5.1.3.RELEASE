package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author mubi
 * @Date 2020/11/3 13:21
 */
@Service
public class UserServiceImp1 implements UserService{
	// 默认byType,然后byName
	// name默认是通过BeanNameGenerator产生的
	@Autowired
	UserDao userDao1;

	@Override
	public void query(int id) {
		System.out.println("UserServiceImp1===");
		userDao1.query(id);
	}
}
