package com.test.service.impl;

import com.test.dao.UserRepo;
import com.test.entity.TbUser;
import com.test.entity.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author mubi
 * @Date 2020/7/4 09:43
 */
@Component("userService")
@Scope("prototype")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUserById(Integer id){
		User user = userRepo.getById(id);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public TbUser getTbUserById(Integer id) {
		return userRepo.getUserById(id);
	}

	@Override
	public Boolean insertAUser(TbUser tbUser) {
		return userRepo.insertAUser(tbUser);
	}


	/**
	 * `UserMapper`接口 如何被实例化？
	 * Jdk动态代理产生的代理对象
	 * mybatis 调用产生
	 * jdk: Proxy.newProxyInstance
	 */
	@Autowired
	private UserMapper userMapper;

	@Override
	public TbUser selectTbUserById(Integer id) {
		return userMapper.selectUserById(id);
	}
}
