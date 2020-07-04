package com.test.service.impl;

import com.test.dao.UserRepo;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author mubi
 * @Date 2020/7/4 09:43
 */
@Component("userService")
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
}
