package com.test.service;

import com.test.entity.TbUser;
import com.test.entity.User;

/**
 * @Author mubi
 * @Date 2020/7/2 09:22
 */
public interface UserService {

	User getUserById(Integer id);

	TbUser getTbUserById(Integer id);

	Boolean insertAUser(TbUser tbUser);
}
