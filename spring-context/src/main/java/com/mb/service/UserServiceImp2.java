package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author mubi
 * @Date 2020/11/3 13:21
 */
//@Service
public class UserServiceImp2 implements UserService{
	@Autowired
	@Resource(type = UserDaoImp1.class)
	UserDao user;

	@Override
	public void query(int id) {
		System.out.println("UserServiceImp2===");
		user.query(id);
	}
}
