package com.test.propagation.required;

import com.test.dao.UserRepo;
import com.test.entity.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author mubi
 * @Date 2020/7/10 10:32
 */
@Service
public class RequiredUser2Service {

	@Autowired
	private UserRepo user2Repository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addRequired() {
		TbUser tbUser = new TbUser("11", "name11", "123");
		user2Repository.insertAUser(tbUser);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addRequiredException() {
		TbUser tbUser = new TbUser("11", "name11", "123");
		user2Repository.insertAUser(tbUser);
		throw new RuntimeException();
	}

}
