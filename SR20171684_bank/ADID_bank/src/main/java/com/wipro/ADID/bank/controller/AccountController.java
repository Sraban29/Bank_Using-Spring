package com.wipro.ADID.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ADID.bank.exception.BadRequestException;
import com.wipro.ADID.bank.exception.ResourceNotFoundException;
import com.wipro.ADID.bank.model.Account;
import com.wipro.ADID.bank.model.Customer;
import com.wipro.ADID.bank.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {
	@Autowired
	AccountService service;
	
	@GetMapping("")
	public List<Account> showAllAccounts()
			throws ResourceNotFoundException{
		return service.list();
	}
	
	@GetMapping("{accountNumber}")
	public Account showAccount(@PathVariable Long accountNumber)
			throws BadRequestException, ResourceNotFoundException{
		return service.findAccount(accountNumber);
	}
	
	@GetMapping("{accountNumber}/customer")
	public Customer getCustomerWithAccountNumber(@PathVariable Long accountNumber)
			throws BadRequestException, ResourceNotFoundException{
		return service.getCustomer(accountNumber);
	}
	
	@PutMapping("")
	public Account updateAccount(@RequestBody Account account )
			throws BadRequestException, ResourceNotFoundException {
		return service.updateAccount(account);
	}
	
	@DeleteMapping("{accountNumber}")
	public void closeAccount(@PathVariable Long accountNumber)
			throws BadRequestException, ResourceNotFoundException {
		service.close(accountNumber);
	}

}
