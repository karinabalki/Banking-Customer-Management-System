package com.bankingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankingsystem.model.Customer;
import com.bankingsystem.model.User;
import com.bankingsystem.service.BankService;
import com.bankingsystem.service.CustomerService;
import com.bankingsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
    private UserService userService;
	
	
	@GetMapping("/allCustomer")
	public String fetchAllCustomer(Model model, HttpSession session){
		
	    String role = (String) session.getAttribute("role");
	
	    if(role == null || !"ADMIN".equalsIgnoreCase(role)) {
		   return "redirect:/login";
	    }
		
		model.addAttribute("listCustomer" ,customerService.getAllCustomer());
	
		return "View_Customer";
	}

	@GetMapping("/addCustomer")
	public String addingCustomerForm(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("saraBank", bankService.getAllBanks());
		model.addAttribute("users", userService.getAllUsers());
		return "New_Customer_Form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String savingCustomer(@ModelAttribute("customer") Customer customer) {
           
	    // 🔥 get user ID from form
	    Long userId = customer.getUser().getId();

	    // 🔥 fetch full user from DB
	    User user = userService.getUserById(userId);

	    // 🔥 set proper user object
	    customer.setUser(user);

       
       customerService.saveCustomer(customer);
       return "redirect:/allCustomer";
	}
	
	
	@GetMapping("/updateCustomer/{id}")
	public String updateCustomerForm( Model model , @PathVariable Long id) {
		
		Customer customer = customerService.getCustomerById(id).orElseThrow();
		model.addAttribute("customer2",customer);
		model.addAttribute("listBank", bankService.getAllBanks());
		model.addAttribute("users", userService.getAllUsers());
		return "Update_Customer_From";
	}
	
	@PostMapping("/uCustomer/{id}")
	public String saveUpdateData(@PathVariable Long id,@ModelAttribute("customer2") Customer customer) {
		
		customer.setId(id);
		customerService.saveCustomer(customer);
		
		return "redirect:/allCustomer";
	}
	
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomerById(@PathVariable Long id) {
		
		customerService.deleteCustomerData(id);
		return "redirect:/allCustomer";
	}
}
