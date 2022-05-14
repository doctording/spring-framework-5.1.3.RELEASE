package com.test.componentbean;

import org.springframework.stereotype.Component;

/**
 * @Author mubi
 * @Date 2020/7/16 23:52
 */
@Component
public class UserManager {
	String userName;

	public UserManager() {
		System.out.println("constructor UserManager()");
	}

	public UserManager(String userName) {
		this.userName = userName;
		System.out.println("constructor UserManager(String userName)");
	}

	@Override
	public String toString() {
		return "UserManager{" +
				"userName='" + userName + '\'' +
				'}';
	}
}