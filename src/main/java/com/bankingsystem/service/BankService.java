package com.bankingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingsystem.model.Bank;
import com.bankingsystem.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	
	public List<Bank> getAllBanks(){
		return bankRepository.findAll();
	}
	
	
	public Optional<Bank> getBankById(@PathVariable Long id){
		return Optional.ofNullable(bankRepository.findById(id).orElseThrow());
	}
	
	public Bank saveBank(@RequestBody Bank bank) {
		return bankRepository.save(bank);
	}
	
	
	public void deleteBankById(@RequestBody Long id) {
		bankRepository.deleteById(id);
	}

}
