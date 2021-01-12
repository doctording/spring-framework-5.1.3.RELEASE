package com.mb.service;

import org.springframework.stereotype.Repository;

/**
 * @Author mubi
 * @Date 2020/11/3 13:22
 */
@Repository
public class UserDaoImp1 implements UserDao{
	@Override
	public void query(int id) {
		System.out.println("UserDaoImp1:" + id);
	}
}
