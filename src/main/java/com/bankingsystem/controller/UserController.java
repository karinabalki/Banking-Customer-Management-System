package com.bankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankingsystem.model.Customer;
import com.bankingsystem.model.User;
import com.bankingsystem.service.CustomerService;
import com.bankingsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;


	@GetMapping("/")
	public String indexView() {
		return "Main";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "LoginPage";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "RegisterPage";
	}
	
	
	@PostMapping("/login")
	public String fetchLoginUser(@RequestParam String username,
			                     @RequestParam String password, 
			                     HttpSession session, Model model) {
		
		User user = userService.getLogin(username, password);
		
		if(user == null) {
			model.addAttribute("error", "Invalid Username or Password");
			return "LoginPage";
		}
		
		session.setAttribute("user", user);
		session.setAttribute("role", user.getRole());
		
		if("ADMIN".equalsIgnoreCase(user.getRole())) {
			return "Admin_Home_Page";
		}
		
        List<Customer> customers = customerService.getCustomerByUser(user);
		
		
		if(customers != null && !customers.isEmpty()) {
			
			model.addAttribute("customers", customers);
			return "Customer_View_page";
			
		}else {
			return "Create_BankAccount_page";
		}

	}
	
	@GetMapping("/adminBankView")
	public String adminBankLogin() {
		return "redirect:/allBanks";
	}
	
	@GetMapping("/adminCustomerView")
	public String adminCustomerLogin() {
		return "redirect:/allCustomer";
	}
	
	
	@PostMapping("/register")
	public String fetchRegister(Model model, User user) {
		
		try {
            userService.getRegister(user);
            model.addAttribute("msg", "Registration successful! Please login.");
            return "LoginPage";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "RegisterPage";
        }
		
	}
	
	
	
	
	
	
}
