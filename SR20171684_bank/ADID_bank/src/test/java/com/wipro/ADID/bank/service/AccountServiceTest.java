package com.wipro.ADID.bank.service;

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

import com.wipro.ADID.bank.exception.BadRequestException;
import com.wipro.ADID.bank.exception.ResourceNotFoundException;
import com.wipro.ADID.bank.model.Account;
import com.wipro.ADID.bank.model.Customer;
import com.wipro.ADID.bank.repository.AccountRepository;
import com.wipro.ADID.bank.repository.CustomerRepository;
import com.wipro.ADID.bank.service.AccountService;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
	
	@Mock
	AccountRepository arepo;
	@Mock
	CustomerRepository crepo;
	
	@InjectMocks
	AccountService service;

	static Customer c1;
	static Customer c2;
	static List<Account> la;
	
	@BeforeAll
	static void init() {
		c1 = new Customer(1L,"Test1",new Account(1L, "Savings", 0.0));
		c2 = new Customer(2L,"Test2",new Account(2L, "Current", 0.0));
		la = new ArrayList<Account>();
		la.add(c1.getAccount());
		la.add(c2.getAccount());
	}
	
	@Test
	void testList() throws ResourceNotFoundException {
		when(arepo.findAll()).thenReturn(la);
		
		List<Account> response = service.list();
		
		assertEquals(la, response);
	}

	@Test
	void testFindAccount() throws BadRequestException, ResourceNotFoundException {
		when(arepo.findById(2L)).thenReturn(Optional.of(c2.getAccount()));
		
		assertEquals(c2.getAccount(), service.findAccount(2L));
	}

	@Test
	void testUpdateAccount() throws BadRequestException, ResourceNotFoundException {
		when(arepo.findById(2L)).thenReturn(Optional.of(c2.getAccount()));
		Account ua = c2.getAccount();
		ua.setBalance(500.0);
		when(arepo.save(ua)).thenReturn(ua);
		
		assertEquals(ua, service.updateAccount(ua));
	}

	@Test
	void testGetCustomer() throws BadRequestException, ResourceNotFoundException {
		when(arepo.findById(2L)).thenReturn(Optional.of(c2.getAccount()));
		List<Customer> l = new ArrayList<Customer>();
		l.add(c2);
		when(crepo.findByAccount(c2.getAccount())).thenReturn(l);
		
		assertEquals(c2, service.getCustomer(2L));
	}

	@Test
	void testClose() {
		
	}

}
