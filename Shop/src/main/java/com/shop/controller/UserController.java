package com.shop.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.service.MailSenderService;
import com.shop.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		
		model.addAttribute("user", new User());
		
		return "views-user-login";
	}
	
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	public String saveuser(@ModelAttribute User user, @RequestParam String confirmPass, Model model) {
		
		String uuid = UUID.randomUUID().toString();
		user.setUuid(uuid);
		
		try {
			userService.save(user, confirmPass);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return "views-user-login";
		}

		String theme = "Thank you for registration";
		String mailBody = "http://localhost:8080/Shop/confirm/" + uuid;
		mailSenderService.sendMail(theme, mailBody, user.getEmail());
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/confirm/{uuid}", method=RequestMethod.GET)
	public String confirm(@PathVariable String uuid) {
		User user = userService.getByUUID(uuid);
		user.setEnabled(true);
		
		userService.update(user);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/userslist", method=RequestMethod.GET)
	public String usersList(Model model) {
		model.addAttribute("users", userService.getWithRole(Role.ROLE_USER));
		model.addAttribute("admins", userService.getWithRole(Role.ROLE_ADMIN));
		
		return "views-admin-userslist";
	}
	
	@RequestMapping(value="/deleteuser/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable String id) {
		
		userService.delete(Integer.parseInt(id));
		
		return "redirect:/userslist";
	}
	
	@RequestMapping(value="/changerole/{id}", method=RequestMethod.GET)
	public String changeRole(@PathVariable String id) {
		
		userService.changeRole(Integer.parseInt(id));
		
		return "redirect:/userslist";
	}
	
	@RequestMapping(value="/changeenabled/{id}", method=RequestMethod.GET)
	public String changeEnabled(@PathVariable String id) {
		
		userService.changeEnabled(Integer.parseInt(id));
		
		return "redirect:/userslist";
	}

}
