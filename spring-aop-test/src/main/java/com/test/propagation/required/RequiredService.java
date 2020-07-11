package com.test.propagation.required;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author mubi
 * @Date 2020/7/10 10:29
 */
@Component("requiredService")
public class RequiredService {

	@Autowired
	private RequiredUser1Service user1Service;

	@Autowired
	private RequiredUser2Service user2Service;

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
	public void transactionExceptionRequired() {
		user1Service.addRequired();
		user2Service.addRequired();
		throw new RuntimeException();
	}

	/**
	 * 此方法没有事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,有异常
	 *
	 * 运行结果是：
	 * 		事务1正常执行
	 * 		事务2有异常，操作回滚
	 */
	public void transactionExceptionInner() {
		user1Service.addRequired();
		user2Service.addRequiredException();
	}

	/**
	 * 此方法有`Propagation.REQUIRED`事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,无异常
	 *
	 * 运行结果是：
	 * 		事务1回滚
	 * 		事务2回滚
	 * 	    此方法异常抛出
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void transactionExceptionRequiredRequired() {
		user1Service.addRequired();
		user2Service.addRequired();
		throw new RuntimeException();
	}

	/**
	 * 此方法有`Propagation.REQUIRED`事务
	 * 		内部调用事务1,无异常
	 * 		内部调用事务2,有异常抛出
	 *
	 * 运行结果是：
	 * 		事务1回滚
	 * 		事务2回滚，抛出异常
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void transactionExceptionRequiredRequiredInner() {
		user1Service.addRequired();
		user2Service.addRequiredException();
		System.out.println("-----transactionExceptionRequiredRequiredInner");
	}

}
