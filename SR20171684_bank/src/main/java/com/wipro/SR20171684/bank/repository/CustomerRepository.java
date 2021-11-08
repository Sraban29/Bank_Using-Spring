package com.wipro.SR20171684.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.SR20171684.bank.model.Account;
import com.wipro.SR20171684.bank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findByName(String name);
	public List<Customer> findByAccount(Account account);

}
