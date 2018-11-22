package com.example.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitys3.Users;
//import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.UserService;

@Controller
@RestController
@RequestMapping("infomation")
public class InfomationController {
	@Autowired
	private UserService userService;
//	@Autowired
//	private AccessControlService accessControlService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Optional<Users> index(Principal principal) {
		Optional<Users> u = userService.findByUserName(principal.getName());
		return u;
	}
}
