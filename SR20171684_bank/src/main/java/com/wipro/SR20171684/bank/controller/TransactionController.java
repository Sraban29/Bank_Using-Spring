package com.wipro.SR20171684.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.SR20171684.bank.exception.BadRequestException;
import com.wipro.SR20171684.bank.exception.InsufficientFundsException;
import com.wipro.SR20171684.bank.exception.NameNotMatchException;
import com.wipro.SR20171684.bank.exception.ResourceNotFoundException;
import com.wipro.SR20171684.bank.service.TransactionService;

@RestController
@RequestMapping("transaction")
public class TransactionController {
	@Autowired
	TransactionService service;
	
	@PostMapping("transfer/from/customer/{idFrom}/to/customer/{idTo}/name/{name}/amount/{amount}")
	void fromCustomerToCustomer(@PathVariable Long idFrom,
			@PathVariable Long idTo,
			@PathVariable String name,
			@PathVariable Double amount)
					throws BadRequestException, ResourceNotFoundException, InsufficientFundsException, NameNotMatchException {
		service.transferFromCustomerToCustomer(idFrom, idTo, name, amount);
	}
	
	@PostMapping("transfer/from/account/{accountNumberFrom}/to/account/{accountNumberTo}/name/{name}/amount/{amount}")
	void fromAccountToAccount(@PathVariable Long accountNumberFrom,
			@PathVariable Long accountNumberTo,
			@PathVariable String name,
			@PathVariable Double amount)
					throws BadRequestException, ResourceNotFoundException, InsufficientFundsException, NameNotMatchException {
		service.transferFromAccountToAccount(accountNumberFrom, accountNumberTo, name, amount);
	}

}
