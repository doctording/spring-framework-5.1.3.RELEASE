package com.mb.service;

/**
 * @Author mubi
 * @Date 2020/11/4 13:01
 */
public class TransferServiceImpl implements TransferService{

	private AccountRepository accountRepository;

	public TransferServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void transfer() {
		accountRepository.transfer();
	}
}
