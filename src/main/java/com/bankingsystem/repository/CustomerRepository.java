package com.bankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingsystem.model.Customer;
import com.bankingsystem.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	List<Customer> findByUser(User user);
	
}
