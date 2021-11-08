package com.wipro.ADID.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ADID.bank.model.Account;
import com.wipro.ADID.bank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findByName(String name);
	public List<Customer> findByAccount(Account account);

}
