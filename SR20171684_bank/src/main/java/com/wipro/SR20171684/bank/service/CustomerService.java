package com.wipro.SR20171684.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.SR20171684.bank.exception.BadRequestException;
import com.wipro.SR20171684.bank.exception.ResourceNotFoundException;
import com.wipro.SR20171684.bank.model.Account;
import com.wipro.SR20171684.bank.model.Customer;
import com.wipro.SR20171684.bank.repository.AccountRepository;
import com.wipro.SR20171684.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public Customer addNewCustomer(Customer customer)
			throws BadRequestException {
		if(customer == null)
			throw new BadRequestException("No Customer Details Given.");
		Account a = customer.getAccount();
		a.setBalance(0.0);
		customer.setAccount(a);
		return customerRepository.save(customer);
	}
	
	public Customer findCustomer(Long id)
			throws BadRequestException, ResourceNotFoundException {
		if(id == null)
			throw new BadRequestException("No Customer ID Given.");
		Optional<Customer> oc = customerRepository.findById(id);
		if(oc.isPresent()) return oc.get();
		throw new ResourceNotFoundException("Customer : " + id + " Not Found.");
	}
	
	public List<Customer> findCustomers(String name)
			throws BadRequestException, ResourceNotFoundException {
		if(name == null)
			throw new BadRequestException("No Customer Name Given.");
		List<Customer> lc = customerRepository.findByName(name);
		if(lc.isEmpty())
			throw new ResourceNotFoundException("Customers With Name : " + name + " Does Not Exists.");
		return lc;
	}
	
	public List<Customer> list()
			throws ResourceNotFoundException{
		List<Customer> lc = customerRepository.findAll();
		if(lc.isEmpty())
			throw new ResourceNotFoundException("No Customer Exists.");
		return lc;
	}
	
	public Account getAccount(Long id)
			throws BadRequestException, ResourceNotFoundException {
		if(id == null)
			throw new BadRequestException("No Customer ID Given.");
		Optional<Customer> oc = customerRepository.findById(id);
		if(oc.isPresent()) return oc.get().getAccount();
		throw new ResourceNotFoundException("Customer : " + id + " Not Found.");
	}
	
	public void CloseAccountOf(Long id)
			throws BadRequestException, ResourceNotFoundException {
		if(id == null)
			throw new BadRequestException("No Customer ID Given.");
		if(customerRepository.existsById(id))
			customerRepository.deleteById(id);
		throw new ResourceNotFoundException("Customer : " + id + " Does Not Exist.");
	}
	
	public Customer updateCustomer(Customer customer)
			throws BadRequestException, ResourceNotFoundException {
		if(customer == null)
			throw new BadRequestException("No Customer Details Given.");
		if(customer.getId() == null)
			throw new BadRequestException("Only Already Present Account Can Be Updated.");
		if(customerRepository.findById(customer.getId()).isEmpty())
			throw new ResourceNotFoundException("Customer : " + customer.getId() + " Not Found.");
		return customerRepository.save(customer);
	}

}
