package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.EmployeePayratesSet;
import com.example.entitys1.Employee;
import com.example.entitys1.EmployeePK;
import com.example.entitys1.PayRates;
import com.example.entitys2.Personal;
import com.example.entitys3.AccessControlKey;
import com.example.entitys3.Users;
import com.example.exception.PersonalToEmployeePayrateException;
import com.example.service.ds1.EmployeeService;
import com.example.service.ds1.PayRateService;
import com.example.service.ds2.PersonalService;
import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.UserService;

@Controller
@RequestMapping("hrm")
@RestController
public class HRManagementController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccessControlService accessControlService;
	
	@Autowired
	private PersonalService personalService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PayRateService payRateService;
	
	@RequestMapping(value = "add-employee", method = RequestMethod.POST)
	public Personal addEmployee(Principal principal, @RequestBody Personal personal) {
		final int FUNCTION_ID = 9; // thêm nhân viên mới
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Personal resPersonal = personalService.save(personal);
			return resPersonal;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "update-to-payrol", method = RequestMethod.POST)
	@Transactional(rollbackFor = PersonalToEmployeePayrateException.class)
	public Employee updateToPayrol(Principal principal, @RequestBody EmployeePayratesSet data) {
		final int FUNCTION_ID = 17;
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Personal pn = personalService.findByID(data.getEmployeeID()).get();
			// create new payrates
			PayRates newPayrates = payRateService.save(data.getPayrate());

			EmployeePK pk = new EmployeePK((int) pn.getEmployee_ID(), newPayrates.getIdPay_Rates());
			Employee payRolEmmployee = new Employee(pk, (int) pn.getEmployee_ID(), pn.getLast_Name(),
					pn.getFirst_Name(), data.getSSN());

			return employeeService.save(payRolEmmployee);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("delete-employee/{id}")
	public int deleteEmployee(Principal principal, @PathVariable long id) {
		final int FUNCTION_ID = 11;
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			int count = 0;
			if (personalService.existsById(id) == true) {
				personalService.deleteByID(id);
				count++;
			}
			
			if (employeeService.existsById((int)id) == true) {
				employeeService.deleteByID((int)id);
				count++;
			}
			return count;
		} else {
			return 0;
		}
		
	}
}
