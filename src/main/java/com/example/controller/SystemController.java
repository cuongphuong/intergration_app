package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.UserRolesCreate;
import com.example.entitys3.AccessControl;
import com.example.entitys3.AccessControlKey;
import com.example.entitys3.Functions;
import com.example.entitys3.Users;
import com.example.exception.AddUserAndRolesException;
import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.FunctionService;
import com.example.service.ds3.UserService;

@Controller
@RequestMapping("users")
@RestController
public class SystemController {
	@Autowired
	private UserService userService;

	@Autowired
	private AccessControlService accessControlService;

	@Autowired
	private FunctionService functionService;

	@Transactional(value = "ds3TransactionManager", rollbackFor = AddUserAndRolesException.class)
	@RequestMapping(value = "create-user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<AccessControl> saveUser(Principal principal, @RequestBody UserRolesCreate object) {
		final int FUNCTION_ID = 13; //thêm user hệ thống
		Users u = userService.findByUserName(principal.getName()).get();
		// check permission
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			// access
			Users uResquest = object.getUser();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Users uCreate = new Users(uResquest.getFullName(), uResquest.getUserName(),
					passwordEncoder.encode(uResquest.getPassword()), uResquest.isEnable());
			Users res = userService.save(uCreate);

			// get roles
			String lstRole[] = object.getRoles().split(";");
			List<AccessControl> lstAccessControl = new ArrayList<AccessControl>();
			for (String item : lstRole) {
				lstAccessControl.add(new AccessControl(new AccessControlKey(Integer.parseInt(item), res.getUserID()),
						functionService.findByID(Integer.parseInt(item)).get(), res, true));
			}

			return accessControlService.saveAll(lstAccessControl);
		} else {
			// not access
			return null;
		}
	}

	@RequestMapping(value = "get-all-role", method = RequestMethod.GET)
	public Iterable<Functions> findAllFunction() {
		return functionService.findAll();
	}
	
	@DeleteMapping("delete-user/{id}")
	public boolean deleteUser(Principal principal, @PathVariable int id) {
		final int FUNCTION_ID = 15; // xóa user hệ thống
		Users u = userService.findByUserName(principal.getName()).get();
		// check permission
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			if (userService.existsById(id) == true) {
				Iterable<AccessControl> a = accessControlService.findAllRolesByUser(id);
				accessControlService.deleteAllByList(a);
				userService.deleteByID(id);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
//	@PutMapping("update-access-control/{userid}")
//	public void updateAccessControllForUser(@RequestBody, @PathVariable int id) {
//		
//	}
}
