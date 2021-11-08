package com.wipro.SR20171684.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SR20171684.bank.exception.BadRequestException;
import com.wipro.SR20171684.bank.exception.InsufficientFundsException;
import com.wipro.SR20171684.bank.exception.NameNotMatchException;
import com.wipro.SR20171684.bank.exception.ResourceNotFoundException;
import com.wipro.SR20171684.bank.model.Account;
import com.wipro.SR20171684.bank.model.Customer;
import com.wipro.SR20171684.bank.repository.AccountRepository;
import com.wipro.SR20171684.bank.repository.CustomerRepository;

@Service
public class TransactionService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public void transferFromCustomerToCustomer(Long from, Long to, String name, Double amount)
			throws BadRequestException, ResourceNotFoundException,
			InsufficientFundsException, NameNotMatchException {
		if(from == null)
			throw new BadRequestException("Customer ID To Transfer From Not Given.");
		if(to == null)
			throw new BadRequestException("Customer ID To Transfer To Not Given.");
		if(name == null)
			throw new BadRequestException("Customer Name To Transfer To is Required.");
		if(amount == null)
			throw new BadRequestException("Amount Not Given");
		if(amount < 0.0)
			throw new BadRequestException("Amount Must Be +ve.");
		
		Optional<Customer> ocf = customerRepository.findById(from);
		Account afrom = null;
		if(ocf.isEmpty())
			throw new ResourceNotFoundException("Customer : " + from + " Does Not Exists.");
		afrom = ocf.get().getAccount();
		
		Optional<Customer> oct = customerRepository.findById(to);
		Account ato = null;
		if(oct.isEmpty())
			throw new ResourceNotFoundException("Customer : " + to + " Does Not Exists.");
		ato = oct.get().getAccount();
		
		if(!oct.get().getName().equals(name))
			throw new NameNotMatchException("Customer Name Must Match To Transfer To.");
		
		if(afrom.getBalance() < amount)
			throw new InsufficientFundsException("Account : " + from + " Has Insufficient Funds.");
		else afrom.setBalance(afrom.getBalance() - amount);
		accountRepository.save(afrom);
		ato.setBalance(ato.getBalance() + amount);
		accountRepository.save(ato);
	}

	public void transferFromAccountToAccount(Long from, Long to, String name, Double amount)
			throws BadRequestException, ResourceNotFoundException,
			InsufficientFundsException, NameNotMatchException {
		if(from == null)
			throw new BadRequestException("Account To Transfer From Not Given.");
		if(to == null)
			throw new BadRequestException("Account To Transfer To Not Given.");
		if(name == null)
			throw new BadRequestException("Customer Name To Transfer To is Required.");
		if(amount == null)
			throw new BadRequestException("Amount Not Given");
		if(amount < 0.0)
			throw new BadRequestException("Amount Must Be +ve.");
		
		Optional<Account> oaf = accountRepository.findById(from);
		Account afrom = null;
		if(oaf.isEmpty())
			throw new ResourceNotFoundException("Account : " + from + " Does Not Exists.");
		afrom = oaf.get();
		
		Optional<Account> oat = accountRepository.findById(to);
		Account ato = null;
		if(oat.isEmpty())
			throw new ResourceNotFoundException("Account : " + to + " Does Not Exists.");
		ato = oat.get();
		
		if(!customerRepository.findByAccount(ato).get(0).getName().equals(name))
			throw new NameNotMatchException("Customer Name Must Match To Transfer To.");

		if(afrom.getBalance() < amount)
			throw new InsufficientFundsException("Account : " + from + " Has Insufficient Funds.");
		else afrom.setBalance(afrom.getBalance() - amount);
		accountRepository.save(afrom);
		ato.setBalance(ato.getBalance() + amount);
		accountRepository.save(ato);
	}

}
