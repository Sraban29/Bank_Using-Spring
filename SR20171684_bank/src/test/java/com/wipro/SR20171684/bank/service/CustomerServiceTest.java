package com.wipro.SR20171684.bank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.SR20171684.bank.exception.BadRequestException;
import com.wipro.SR20171684.bank.exception.ResourceNotFoundException;
import com.wipro.SR20171684.bank.model.Account;
import com.wipro.SR20171684.bank.model.Customer;
import com.wipro.SR20171684.bank.repository.AccountRepository;
import com.wipro.SR20171684.bank.repository.CustomerRepository;
import com.wipro.SR20171684.bank.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	AccountRepository arepo;
	@Mock
	CustomerRepository crepo;
	
	@InjectMocks
	CustomerService service;
	
	static Customer c1;
	static Customer c2;
	static Customer c3;
	static List<Customer> lc;
	
	@BeforeAll
	static void init() {
		c1 = new Customer(1L,"Test1",new Account(1L, "Savings", 0.0));
		c2 = new Customer(2L,"Test2",new Account(2L, "Current", 0.0));
		c3 = new Customer(3L,"Test2",new Account(2L, "Savings", 0.0));
		lc = new ArrayList<Customer>();
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
	}

	@Test
	void testAddNewCustomer() throws BadRequestException {
		Customer uc = new Customer();
		Account ua = c2.getAccount();
		ua.setBalance(0.0);
		uc.setAccount(ua);
		uc.setId(c2.getId());
		uc.setName(c2.getName());
		when(crepo.save(uc)).thenReturn(uc);
		
		assertEquals(uc, service.addNewCustomer(uc));
	}

	@Test
	void testFindCustomer() throws BadRequestException, ResourceNotFoundException {
		when(crepo.findById(2L)).thenReturn(Optional.of(c2));
		
		assertEquals(c2, service.findCustomer(2L));
	}

	@Test
	void testFindCustomers() throws BadRequestException, ResourceNotFoundException {
		List<Customer> lt = new ArrayList<Customer>();
		lt.add(c2);
		lt.add(c3);
		when(crepo.findByName("Test2")).thenReturn(lt);
		
		assertEquals(lt,service.findCustomers("Test2"));
	}

	@Test
	void testList() throws ResourceNotFoundException {
		when(crepo.findAll()).thenReturn(lc);
		
		List<Customer> response = service.list();
		
		assertEquals(lc, response);
	}

	@Test
	void testGetAccount() throws BadRequestException, ResourceNotFoundException {
		when(crepo.findById(2L)).thenReturn(Optional.of(c2));
		
		assertEquals(c2.getAccount(), service.getAccount(2L));
	}

	@Test
	void testCloseAccountOf() {
		
	}

	@Test
	void testUpdateCustomer() throws BadRequestException, ResourceNotFoundException {
		when(crepo.findById(2L)).thenReturn(Optional.of(c2));
		Customer uc = new Customer();
		uc.setAccount(c2.getAccount());
		uc.setId(c2.getId());
		uc.setName("Changed");
		when(crepo.save(uc)).thenReturn(uc);
		
		assertEquals(uc, service.updateCustomer(uc));
	}

}
