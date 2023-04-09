package com.smart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository; 
    
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "smart contact maneger" );
		
		return "home";
	}
	  
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "about contact maneger" );
		
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register contact maneger" );
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	//handler for registering user
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement" ,defaultValue = "false" )boolean agreement , Model model ) 
		{	
		if(!agreement)
		{
			System.out.println("you jave not agreed to terms and conditions");
		}
		
		user.setRole("ROLE_USER");
		user.setEnabled(true);
			
		User result = this.userRepository.save(user);
		
		model.addAttribute("user",result);
		System.out.println("Agreement"+ agreement);
		System.out.println("USER" + user);

		return "signup";
	}
	
	
	
	
	}
