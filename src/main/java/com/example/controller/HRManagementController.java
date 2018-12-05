package com.example.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.EmployeePayratesSet;
import com.example.custommodel.IntergrationInfoPersonal;
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
		final int FUNCTION_ID = 9;
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Personal resPersonal = personalService.save(personal);
			return resPersonal;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "update-employee", method = RequestMethod.PUT)
	public Personal upadteEmployee(Principal principal, @RequestBody Personal personal) {
		final int FUNCTION_ID = 9;
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Personal resPersonal = personalService.save(personal);
			
			Employee employee = employeeService.getByUserID((int)resPersonal.getEmployee_ID());
			employee.setFirst_Name(resPersonal.getFirst_Name());
			employee.setLast_Name(resPersonal.getLast_Name());
			employeeService.save(employee);
			return resPersonal;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "update-to-payrol", method = RequestMethod.POST)
	@Transactional(value = "ds1TransactionManager", rollbackFor = PersonalToEmployeePayrateException.class)
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
		System.out.println("id la : " + id);
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			int count = 0;
			if (personalService.existsById(id) == true) {
				personalService.deleteByID(id);
				count++;
			}

			if (employeeService.checkExist((int) id) == true) {
				Employee employee = null;
				employee = employeeService.getByUserID((int)id);
				payRateService.deleteByID(employee.getPayRate().getIdPay_Rates());
				employeeService.deleteByID((int) id);
				count++;
			}
			return count;
		} else {
			return 0;
		}
	}
	
	@GetMapping("get-peeonal")
	public Optional<Personal> getPersonalByID(@RequestParam("id") long id) {
		return personalService.findByID(id);
	}

	@GetMapping("get-all-employee")
	public Map<Long, IntergrationInfoPersonal> getAllEmployee(Principal principal, @RequestParam("page") int page) {
		final int FUNCTION_ID = 11;
		Users u = userService.findByUserName(principal.getName()).get();
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			List<Personal> lstPersonal = personalService.findByPage(page);
			List<Employee> lstEmployee = employeeService.getPersonalBySegmentID(
					(int) lstPersonal.get(0).getEmployee_ID(),
					(int) lstPersonal.get(lstPersonal.size() - 1).getEmployee_ID());

			// merge two list

			Map<Long, IntergrationInfoPersonal> mergeLst = new HashMap<>();
			for (Personal item : lstPersonal) {
				mergeLst.put(item.getEmployee_ID(),
						new IntergrationInfoPersonal(item.getEmployee_ID(), item.getFirst_Name(), item.getLast_Name(),
								item.getMiddle_Initial(), item.getAddress1(), item.getAddress2(), item.getCity(),
								item.getState(), item.getZip(), item.getEmail(), item.getPhone_Number(),
								item.getSocial_Security_Number(), item.getSocial_Security_Number(),
								item.getMarital_Status(), item.getGender(), item.getShareholder_Status(),
								item.getEthnicity(), false, false));
			}

			for (Employee iEmployee : lstEmployee) {
				if (mergeLst.containsKey(Long.valueOf(iEmployee.getPk().getEmployee_Number())) == true) {
					mergeLst.get(Long.valueOf(iEmployee.getPk().getEmployee_Number())).setExist(true);

					IntergrationInfoPersonal iPersonalTmp = mergeLst
							.get(Long.parseLong(String.valueOf(iEmployee.getPk().getEmployee_Number())));

					if (iEmployee.getFirst_Name().equals(iPersonalTmp.getFirst_Name())
							&& iEmployee.getLast_Name().equals(iPersonalTmp.getLast_Name())) {
						mergeLst.get(Long.valueOf(iEmployee.getPk().getEmployee_Number())).setSame(true);
					}
				}
			}
			return mergeLst;
		} else {
			return null;
		}
	}
}
