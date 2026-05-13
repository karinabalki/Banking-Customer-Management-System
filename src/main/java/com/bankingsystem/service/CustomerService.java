package com.bankingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingsystem.model.Customer;
import com.bankingsystem.model.User;
import com.bankingsystem.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	
	public Optional<Customer> getCustomerById(@PathVariable Long id){
		return Optional.ofNullable(customerRepository.findById(id).orElseThrow());
	}
	
	public List<Customer> getCustomerByUser(User user){
		return customerRepository.findByUser(user);
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	public void deleteCustomerData(@PathVariable Long id) {
		customerRepository.deleteById(id);
	}
	
	
	
}
