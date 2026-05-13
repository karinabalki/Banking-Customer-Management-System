package com.bankingsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="Customer_Details")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="Name")
	private String name;
	@Column(name="Address")
	private String address;
	@Column(name="Age")
	private int age;
	
	@ManyToOne
	@JoinColumn(name="Bank_Id")
	private Bank bank;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Customer() {
		
	}

	public Customer(String name, String address, int age, Bank bank, User user) {
	
		this.name = name;
		this.address = address;
		this.age = age;
		this.bank = bank;
		this.user = user;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
