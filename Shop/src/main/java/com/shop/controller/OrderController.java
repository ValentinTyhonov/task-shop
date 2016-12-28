package com.shop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.User;
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
		
		User user = userService.getOne(Integer.parseInt(principal.getName()));
		int totalPrice = 0;
		
		try {
			Order order = orderService.getNotPaidByUser(user);
			List<Product> products = order.getProducts();
			
			for (Product product : products) {
				totalPrice += product.getPrice();
			}
			
			model.addAttribute("products", products);
			model.addAttribute("totalPrice", totalPrice);
		} catch(NoResultException e) {
			model.addAttribute("products", new ArrayList<Product>());
			model.addAttribute("totalPrice", totalPrice);
		}
		
		return "views-order-cart";
	}
	
	@RequestMapping(value="/removefromcart/{id}", method=RequestMethod.GET)
	public String removeFromCart(Principal principal, @PathVariable int id) {
		
		User user = userService.getOne(Integer.parseInt(principal.getName()));
		Order order = orderService.getNotPaidByUser(user);
		List<Product> products = order.getProducts();
				
		Iterator<Product> iterator = products.listIterator();
		while (iterator.hasNext()) {
			if(iterator.next().getId() == id) {
				iterator.remove();
			}
		}

		order.setProducts(products);
		orderService.update(order);
		
		return "redirect:/opencart";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout(Principal principal, @RequestParam("totalPrice") String totalPrice, Model model) {
		
		User user = userService.getOne(Integer.parseInt(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("totalPrice", totalPrice);
		
		return "views-order-checkout";
	}
	
	@RequestMapping(value="/placeorder", method=RequestMethod.POST)
	public String placeOrder(Principal principal, @RequestParam("totalPrice") String totalPrice, @RequestParam String shipping_first_name, 
			@RequestParam String shipping_last_name, @RequestParam String billing_card_number, @RequestParam String billing_first_name, 
			@RequestParam String billing_last_name, Model model) {
		
		User user = userService.getOne(Integer.parseInt(principal.getName()));
		Order order = orderService.getNotPaidByUser(user);
		
		orderService.placeOrder(order, Integer.parseInt(totalPrice));
		String all = "";
		for (Product product : order.getProducts()) {
			all += product.getName() + " $" + product.getPrice() + "\n";
		}
		
		String theme = "Your purchase is completed";
		String mailBody = "Dear " + shipping_first_name + " " + shipping_last_name + 
				"! You have just paid your purchase. Card number: " + billing_card_number + 
				" (card owner - " + billing_first_name + " " + billing_last_name + 
				").\n\n" + all + "\nTotal price: $" + totalPrice;
		mailSenderService.sendMail(theme, mailBody, user.getEmail());
		
		return "redirect:/";
	}
	
}
