package com.mb.service;

import org.springframework.stereotype.Repository;

/**
 * @Author mubi
 * @Date 2020/11/3 13:22
 */
//@Repository
public class UserDaoImp2 implements UserDao{
	@Override
	public void query(int id) {
		System.out.println("UserDaoImp2:" + id);
	}
}
