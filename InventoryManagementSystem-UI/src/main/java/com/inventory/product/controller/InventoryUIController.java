package com.inventory.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.product.service.InventoryUIService;

@Controller
public class InventoryUIController {
	
	private InventoryUIService inventoryUIService;
	
	@Autowired
	public InventoryUIController(InventoryUIService inventoryUIService) {
		this.inventoryUIService= inventoryUIService;
	}
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", inventoryUIService.getProducts());
		return "products";
	}
	
	@GetMapping("/error")
	public String errorPage() {
		return "customerror";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
