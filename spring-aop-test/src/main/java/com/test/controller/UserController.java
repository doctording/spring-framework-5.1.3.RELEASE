package com.test.controller;

import com.test.aop.HttpLogger;
import com.test.aop.OperateLog;
import com.test.entity.TbUser;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author mubi
 * @Date 2020/11/4 23:37
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@HttpLogger
	@OperateLog(operationType = "get", operationName = "获取用户")
	public User getById(int id) {
		return userService.getUserById(id);
	}

	@HttpLogger
	public TbUser getTbUserById(int id) {
		return userService.getTbUserById(id);
	}
}
