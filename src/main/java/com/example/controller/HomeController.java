package com.example.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitys3.AccessControl;
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

	@RequestMapping(value = "check", method = RequestMethod.GET)
	public Optional<Users> check(Principal principal) {
		Optional<Users> u = userService.findByUserName(principal.getName());
		if (u != null)
			return u;
		else
			return null;

	}

	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public Iterable<AccessControl> roles(Principal principal) {
		Users u = userService.findByUserName(principal.getName()).get();
		return accessControlService.findAllRolesByUser(u.getUserID());
	}

//	@RequestMapping(value="role", method = RequestMethod.GET)
//	public String role(Principal principal) {
//		final int FUNCTION_ID = 1;
//		Users u = userService.findByUserName(principal.getName()).get();
//		if(accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
//			return "True";
//		} else {
//			return "Not right to access";
//		}
//	}
	@RequestMapping(value = "checkuser", method = RequestMethod.GET)
	public boolean roles(@RequestParam("username") String username) {
		Users u = userService.findByUserName(username).get();
		if(userService.existsById(u.getUserID()) == true) {
			return true;
		}
		return false;
		
	}
	
}
