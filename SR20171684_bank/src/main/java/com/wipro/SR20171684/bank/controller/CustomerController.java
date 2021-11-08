package com.wipro.SR20171684.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.SR20171684.bank.exception.BadRequestException;
import com.wipro.SR20171684.bank.exception.ResourceNotFoundException;
import com.wipro.SR20171684.bank.model.Account;
import com.wipro.SR20171684.bank.model.Customer;
import com.wipro.SR20171684.bank.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService service;
	
	@GetMapping("")
	public List<Customer> showAllCustomers()
			throws ResourceNotFoundException{
		return service.list();
	}
	
	@GetMapping("{id}")
	public Customer showCustomer(@PathVariable Long id)
			throws BadRequestException, ResourceNotFoundException{
		return service.findCustomer(id);
	}
	
	@GetMapping("{id}/account")
	public Account getAccount(@PathVariable Long id)
			throws BadRequestException, ResourceNotFoundException{
		return service.getAccount(id);
	}
	
	@GetMapping("name/{name}")
	public List<Customer> showCustomersByName(@PathVariable String name)
			throws BadRequestException, ResourceNotFoundException{
		return service.findCustomers(name);
	}
	
	@PostMapping("")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
			throws BadRequestException {
		return new ResponseEntity<Customer>(
				service.addNewCustomer(customer),
				HttpStatus.CREATED
				);
	}
	
	@PutMapping("")
	public Customer updateCustomer(@RequestBody Customer customer)
			throws BadRequestException, ResourceNotFoundException {
		return service.updateCustomer(customer);
	}
	
	@DeleteMapping("{id}")
	public void closeAccount(@PathVariable Long id)
			throws BadRequestException, ResourceNotFoundException {
		service.CloseAccountOf(id);
	}

}
