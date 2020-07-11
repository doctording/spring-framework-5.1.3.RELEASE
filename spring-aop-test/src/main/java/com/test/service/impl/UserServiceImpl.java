package com.test.service.impl;

import com.test.dao.UserRepo;
import com.test.entity.TbUser;
import com.test.entity.User;
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
}
