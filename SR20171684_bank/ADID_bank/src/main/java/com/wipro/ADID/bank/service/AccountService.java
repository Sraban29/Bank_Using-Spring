package com.wipro.ADID.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ADID.bank.exception.BadRequestException;
import com.wipro.ADID.bank.exception.ResourceNotFoundException;
import com.wipro.ADID.bank.model.Account;
import com.wipro.ADID.bank.model.Customer;
import com.wipro.ADID.bank.repository.AccountRepository;
import com.wipro.ADID.bank.repository.CustomerRepository;

@Service
public class AccountService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> list()
			throws ResourceNotFoundException{
		List<Account> la = accountRepository.findAll();
		if(la.isEmpty())
			throw new ResourceNotFoundException("No Account Exists.");
		return la;
	}
	
	public Account findAccount(Long accountNumber)
			throws BadRequestException, ResourceNotFoundException {
		if(accountNumber == null)
			throw new BadRequestException("No Account Number Given.");
		Optional<Account> oa = accountRepository.findById(accountNumber);
		if(oa.isPresent()) return oa.get();
		throw new ResourceNotFoundException("Account : " + accountNumber + " Not Found.");
	}
	
	public Account updateAccount(Account account)
			throws BadRequestException, ResourceNotFoundException {
		if(account == null)
			throw new BadRequestException("No Account Details Given.");
		if(account.getAccount_number() == null)
			throw new BadRequestException("Only Already Present Account Can Be Updated.");
		if(accountRepository.findById(account.getAccount_number()).isEmpty())
			throw new ResourceNotFoundException("Account : " + account.getAccount_number() + " Not Found.");
		return accountRepository.save(account);
	}
	
	public Customer getCustomer(Long accountNumber)
			throws BadRequestException, ResourceNotFoundException {
		if(accountNumber == null)
			throw new BadRequestException("No Account Number Given.");
		Optional<Account> oa = accountRepository.findById(accountNumber);
		if(oa.isPresent())
			return customerRepository.findByAccount(oa.get()).get(0);
		throw new ResourceNotFoundException("Customer with Account : " + accountNumber + " Not Found.");
	}
	
	public void close(Long accountNumber)
			throws BadRequestException, ResourceNotFoundException {
		if(accountNumber == null)
			throw new BadRequestException("No Account Number Given.");
		Optional<Account> oa = accountRepository.findById(accountNumber);
		if(oa.isPresent())
			customerRepository.delete(customerRepository.findByAccount(oa.get()).get(0));
		throw new ResourceNotFoundException("Account : " + accountNumber + " Does Not Exists.");
	}

}
