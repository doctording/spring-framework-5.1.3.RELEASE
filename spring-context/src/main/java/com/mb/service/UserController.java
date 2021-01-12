package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Author mubi
 * @Date 2020/11/3 18:39
 */
@Controller
public class UserController {
	@Autowired
//	@Resource(type = UserServiceImp1.class)
	UserService userService1;

	public void query(int id){
		userService1.query(id);
	}
}
