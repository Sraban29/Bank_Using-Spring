package com.wipro.ADID.bank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.ADID.bank.controller.AccountController;
import com.wipro.ADID.bank.controller.CustomerController;
import com.wipro.ADID.bank.controller.TransactionController;

@SpringBootTest(classes = com.wipro.ADID.bank.ADIDBankApplication.class)
class ADIDBankApplicationTests {
	
	@Autowired
	AccountController act;
	@Autowired
	CustomerController cct;
	@Autowired
	TransactionController tct;

	@Test
	void contextLoadsAccounts() {
		assertThat(act).isNotNull();
	}
	
	@Test
	void contextLoadsCustomer() {
		assertThat(cct).isNotNull();
	}
	
	@Test
	void contextLoadsTransaction() {
		assertThat(tct).isNotNull();
	}
	
	@Test
	void contextLoads() {
	}

}
