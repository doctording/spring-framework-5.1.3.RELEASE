package com.test.dao;

import com.test.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * @Author mubi
 * @Date 2020/7/2 09:22
 */
@Repository
public class UserRepo {

	private static final Log logger = LogFactory.getLog(UserRepo.class);

	public User getById(Integer id){
		logger.info("getById:" + id);
		return new User(id, "abc" + id);
	}
}
