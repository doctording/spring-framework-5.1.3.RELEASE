package com.test.propagation.requiresnew;

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
public class RequiresNewUser1Service {

	@Autowired
	private UserRepo user1Repository;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addRequiresNew() {
		TbUser tbUser = new TbUser("10", "name10", "123");
		user1Repository.insertAUser(tbUser);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addRequiresNewException() {
		TbUser tbUser = new TbUser("10", "name10", "123");
		user1Repository.insertAUser(tbUser);
		throw new RuntimeException();
	}

}
