package com.bankingsystem.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Bank_Details")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="BankName")
	private String name;
	@Column(name="IFSC")
	private String ifsc;
	@Column(name="BankBranch")
	private String branch;
	
	@OneToMany(mappedBy = "bank")
	private List<Customer> customer;

	public Bank() {
		
	}

	public Bank(String name, String ifsc, String branch, List<Customer> customer) {
	
		this.name = name;
		this.ifsc = ifsc;
		this.branch = branch;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	

	
	
	

}
