package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.AccessControlResUpdate;
import com.example.custommodel.AccessControlUpdate;
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
		final int FUNCTION_ID = 13; // thêm user hệ thống
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

	@RequestMapping(value = "update-user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Users updateUser(Principal principal, @RequestBody Users object) {
		final int FUNCTION_ID = 14; // update user hệ thống
		Users u = userService.findByUserName(principal.getName()).get();
		// check permission
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Users res = userService.save(object);
			return res;
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
		Iterable<AccessControl> a = accessControlService.findAllRolesByUser(id);

		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
//			if (userService.existsById(id) == true) {
//				
//			} else {
//				return false;
//			}
			accessControlService.deleteAllByList(a);
			userService.deleteByID(id);
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("lst-user")
	public Iterable<Users> listUser(Principal principal) {
		final int FUNCTION_ID = 18; // lấy danh sasch user
		Users u = userService.findByUserName(principal.getName()).get();
		// check permission
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			return userService.findAll();
		} else {
			return null;
		}
	}

	@RequestMapping("get-uer-byid")
	public Optional<Users> getUserByID(@RequestParam("id") int id) {
		return userService.findByID(id);
	}

	@GetMapping("get-change-accessct")
	public List<AccessControlResUpdate> returnAccessControl(@RequestParam("id") int id) {
		Map<Integer, AccessControl> lstAccess = new HashMap<Integer, AccessControl>();
		Iterable<AccessControl> lstTrue = accessControlService.findAllRolesByUser(id);

		Iterable<Functions> funcs = functionService.findAll();

		for (Functions item : funcs) {
			lstAccess.put(Integer.parseInt(String.valueOf(id + item.getFunctionID())),
					new AccessControl(item.getFunctionID(), id, false));
		}

		for (AccessControl itemTrue : lstTrue) {
			if (lstAccess.containsKey(
					Integer.parseInt(String.valueOf(itemTrue.getUserID() + itemTrue.getFunctionID()))) == true) {
				lstAccess.get(Integer.parseInt(String.valueOf(itemTrue.getUserID() + itemTrue.getFunctionID())))
						.setStatus(true);
			}
		}
		
		List<AccessControlResUpdate> lstRes = new ArrayList<>();
		
		for(AccessControl item : lstAccess.values()) {
			Functions func = functionService.findByID(item.getFunctionID()).get();
			lstRes.add(new AccessControlResUpdate(item.getFunctionID(),func.getFunctionName() , item.getUserID(), item.isStatus()));
		}

		return lstRes;
	}

	@PostMapping("update-access-control")
	public boolean updateAccessControl(@RequestBody AccessControlUpdate updateAcc) {
		List<AccessControl> lstAccess = updateAcc.getConvertToLstAccessControl();
		List<AccessControl> lstUpdate = new ArrayList<>();
		List<AccessControl> lstDelete = new ArrayList<>();
		for (AccessControl item : lstAccess) {

			if (accessControlService.check(item.getAccessControlKey()) == true) { // ton tai trong database
				if (item.isStatus() == false) {
					lstDelete.add(item);
				}
			} else { // không tồn tại
				if(item.isStatus() == true) {
					Functions function = functionService.findByID(item.getFunctionID()).get();
					Users user = userService.findByID(item.getUserID()).get();
					item.setFunction(function);
					item.setUser(user);
					lstUpdate.add(item);
				}
			}
		}
		if(lstUpdate.size() > 0) accessControlService.saveAll(lstUpdate);
		if(lstDelete.size() > 0) accessControlService.deleteAllByList(lstDelete);
		return true;
	}

//	@PutMapping("update-access-control/{userid}")
//	public void updateAccessControllForUser(@RequestBody, @PathVariable int id) {
//		
//	}
}
