package com.wipro.ADID.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ADID.bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
