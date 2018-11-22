package com.example.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitys3.AccessControlKey;
import com.example.entitys3.Users;
import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.UserService;


@Controller
@RequestMapping("home")
@RestController
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private AccessControlService accessControlService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Optional<Users> index(Principal principal) {
		Optional<Users> u = userService.findByUserName(principal.getName());
		System.out.println("Name : " + principal.getName() );
		return u;
	}
	
	@RequestMapping(value="role", method = RequestMethod.GET)
	public String role(Principal principal) {
		final int FUNCTION_ID = 1;
		Users u = userService.findByUserName(principal.getName()).get();
		if(accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			return "True";
		} else {
			return "False";
		}
	}
}
