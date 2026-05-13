package com.bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
	
	

}
