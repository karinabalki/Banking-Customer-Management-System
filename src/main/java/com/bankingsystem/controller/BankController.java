package com.bankingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bankingsystem.model.Bank;
import com.bankingsystem.service.BankService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/allBanks")
	public String fetchAllBanks(Model model, HttpSession session){
			
		String role = (String) session.getAttribute("role");
		
		if(role == null || !"ADMIN".equalsIgnoreCase(role)) {
			return "redirect:/login";
		}
		
		model.addAttribute("listBanks", bankService.getAllBanks());
		
		return "ViewBanks";
	}
	
	
	@GetMapping("/addBank")
	public String addingBankForm(Model model) {
		Bank bank1 = new Bank();
		model.addAttribute("bank", bank1);
		return "New_Bank_Form";
		
	}
	
	@PostMapping("/saveBank")
	public String savingBank(@ModelAttribute("bank") Bank bank) {
		
		bankService.saveBank(bank);
		return "redirect:/allBanks";
	}
	
	
	@GetMapping("/updateBank/{id}")
	public String updatingBankData(Model model, @PathVariable Long id) {
		Bank bank = bankService.getBankById(id).orElseThrow();
		model.addAttribute("bank2", bank);
		
		return "Update_Bank_Update";
	}
	
	@PostMapping("/uBank/{id}")
	public String updatedBank(@PathVariable Long id, @ModelAttribute Bank bank) {
		
		bank.setId(id);
		
		bankService.saveBank(bank);
		
		return "redirect:/allBanks";
	}
	
	
	@GetMapping("/deleteBank/{id}")
	public String deleteBankId(@PathVariable Long id) {
		
		bankService.deleteBankById(id);
		return "redirect:/allBanks";
		
	}

}
