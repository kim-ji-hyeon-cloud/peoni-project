package com.peoni.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.peoni.project.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final IProductService productService;

	
	@GetMapping("/main")
	public String mainPage(Model model) {
		model.addAttribute("hotProducts", productService.getHotProducts());
		model.addAttribute("newProducts", productService.getNewProducts());
		return "main";
	}
	
}
