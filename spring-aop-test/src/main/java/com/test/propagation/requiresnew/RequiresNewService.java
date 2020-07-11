package com.test.propagation.requiresnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author mubi
 * @Date 2020/7/10 10:29
 */
@Component("requiresNewService")
public class RequiresNewService {

	@Autowired
	private RequiresNewUser1Service user1Service;

	@Autowired
	private RequiresNewUser2Service user2Service;

	/**
	 * 此方法没有事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,无异常
	 *
	 * 运行结果是：
	 * 		事务1正常执行
	 * 		事务2正常执行
	 * 	    此方法抛异常
	 */
	public void transactionExceptionRequiresNew() {
		user1Service.addRequiresNew();
		user2Service.addRequiresNew();
		throw new RuntimeException();
	}

	/**
	 * 此方法没有事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,有异常
	 *
	 * 运行结果是：
	 * 		事务1正常执行
	 * 		事务2有异常，回滚
	 *
	 */
	public void transactionExceptionRequiresNewInner() {
		user1Service.addRequiresNew();
		user2Service.addRequiresNewException();
	}

	/**
	 * 此方法有`Propagation.REQUIRES_NEW`事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,有异常
	 *
	 * 运行结果是：
	 * 		事务1正常执行
	 * 		事务2回滚，抛出异常
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void transactionExceptionRequiresNewException() {
		user1Service.addRequiresNew();
		user2Service.addRequiresNewException();
	}

	/**
	 * 此方法有`Propagation.REQUIRES_NEW`事务
	 * 		内部调用事务1,有异常
	 * 		内部调用事务2,无异常
	 *
	 * 运行结果是：
	 * 		事务1回滚,抛出异常
	 * 		事务2得不到执行
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void transactionExceptionRequiresNewException2() {
		user1Service.addRequiresNewException();
		System.out.println("----transactionExceptionRequiresNewException2");
		user2Service.addRequiresNew();
	}

	/**
	 * 此方法有`Propagation.REQUIRES_NEW`事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,无异常
	 *
	 * 运行结果是：
	 * 		事务1正常执行
	 * 		事务2正常执行
	 * 	    此方法抛异常
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void transactionExceptionRequiresNewExceptionRuntime() {
		user1Service.addRequiresNew();
		user2Service.addRequiresNew();
		throw new RuntimeException();
	}
}