package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.dao.ProductDao;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value={ "/", "/index" }, method=RequestMethod.GET)
	public String home(Model model) {
		
		int[] priceLimit = productService.getPriceLimit();		
		model.addAttribute("priceLimit", priceLimit);
		
		return "views-product-home";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String loginprocesing(){	
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout() {
		return "redirect:/";
	}

}
