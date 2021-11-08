package com.wipro.SR20171684.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.SR20171684.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
