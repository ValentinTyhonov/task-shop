package com.shop.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Product;
import com.shop.service.MailSenderService;
import com.shop.service.OrderService;
import com.shop.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSenderService mailSenderService;
		
	@RequestMapping(value="/opencart", method=RequestMethod.GET)
	public String openCart(Principal principal, Model model) {
				
		try {
			model.addAttribute("products", orderService.getNotPaidByUser(userService.getOne(Integer.parseInt(principal.getName()))).getProducts());
			model.addAttribute("totalPrice", orderService.totalPrice(orderService.getNotPaidByUser(userService.getOne(Integer.parseInt(principal.getName()))).getProducts()));
		} catch(NoResultException e) {
			model.addAttribute("products", new ArrayList<Product>());
			model.addAttribute("totalPrice", "0");
		}
		
		return "views-order-cart";
	}
	
	@RequestMapping(value="/removefromcart/{id}", method=RequestMethod.GET)
	public String removeFromCart(Principal principal, @PathVariable int id) {
		orderService.removeFromCart(Integer.parseInt(principal.getName()), id);
		return "redirect:/opencart";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout(Principal principal, @RequestParam("totalPrice") String totalPrice, Model model) {
		model.addAttribute("user", userService.getOne(Integer.parseInt(principal.getName())));
		model.addAttribute("totalPrice", totalPrice);
		return "views-order-checkout";
	}
	
	@RequestMapping(value="/placeorder", method=RequestMethod.POST)
	public String placeOrder(Principal principal, @RequestParam("totalPrice") String totalPrice, @RequestParam String shipping_first_name, 
			@RequestParam String shipping_last_name, @RequestParam String billing_card_number, @RequestParam String billing_first_name, 
			@RequestParam String billing_last_name, Model model) {

		orderService.placeOrder(orderService.getNotPaidByUser(userService.getOne(Integer.parseInt(principal.getName()))), Integer.parseInt(totalPrice));
		
		String theme = "Your purchase is completed";
		String mailBody = "Dear " + shipping_first_name + " " + shipping_last_name + 
				"! You have just paid your purchase. Card number: " + billing_card_number + 
				" (card owner - " + billing_first_name + " " + billing_last_name + 
				").\n\n" + orderService.listOfBoughtProducts(orderService.getNotPaidByUser(userService.getOne(Integer.parseInt(principal.getName())))) + 
				"\nTotal price: $" + totalPrice;
		mailSenderService.sendMail(theme, mailBody, userService.getOne(Integer.parseInt(principal.getName())).getEmail());
		
		return "redirect:/";
	}
	
}
