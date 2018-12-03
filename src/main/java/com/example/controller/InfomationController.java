package com.example.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.IntergrationBeneftPlan;
import com.example.custommodel.IntergrationTotalEarnings;
import com.example.custommodel.IntergrationVacationDays;
import com.example.entitys1.Employee;
import com.example.entitys2.Personal;
import com.example.entitys3.AccessControlKey;
import com.example.entitys3.Users;
import com.example.service.ds1.EmployeeService;
import com.example.service.ds2.PersonalService;
import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.UserService;

@Controller
@RestController
@RequestMapping("infomation")
public class InfomationController {
	@Autowired
	private UserService userService;
	@Autowired
	private AccessControlService accessControlService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PersonalService personalService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Principal principal) {
		return "Service API get employee infomation";
	}

	@RequestMapping(value = "total-earnings", method = RequestMethod.GET)
	public Map<Long, IntergrationTotalEarnings> geTotalEarnings(Principal principal, @RequestParam("page") int page) {
		final int FUNCTION_ID = 1; // function_tong_thu_nhap_nhan_vien
		Users u = userService.findByUserName(principal.getName()).get();
		// check acess control
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			List<Employee> lstEmployee = employeeService.findByPage(page);
			List<Personal> lstPersonal = personalService.getPersonalBySegmentID(
					lstEmployee.get(0).getPk().getEmployee_Number(),
					lstEmployee.get(lstEmployee.size() - 1).getPk().getEmployee_Number());

			// merge two list

			Map<Long, IntergrationTotalEarnings> mergeLst = new HashMap<>();
			for (Personal iPersonal : lstPersonal) {
				mergeLst.put(iPersonal.getEmployee_ID(),
						new IntergrationTotalEarnings(iPersonal.getEmployee_ID(), iPersonal.getFirst_Name(),
								iPersonal.getLast_Name(), iPersonal.getGender(), iPersonal.getEthnicity(),
								iPersonal.getAddress1(), iPersonal.getAddress2(), iPersonal.getPhone_Number()));
			}

			for (Employee iEmployee : lstEmployee) {
				if (mergeLst.containsKey(Long.valueOf(iEmployee.getPk().getEmployee_Number())) == true) {
					mergeLst.get(Long.valueOf(iEmployee.getPk().getEmployee_Number()))
							.setTotalEarnings(iEmployee.getPayRate().getPay_Amount());
				}
			}
			return mergeLst;
		} else
			return null;
	}

	@RequestMapping(value = "vacationdays", method = RequestMethod.GET)
	public Map<Long, IntergrationVacationDays> getVacationDays(Principal principal, @RequestParam("page") int page) {
		final int FUNCTION_ID = 2; // function_tong_ ngày nghĩ nhân viên
		Users u = userService.findByUserName(principal.getName()).get();
		// check acess control
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			List<Employee> lstEmployee = employeeService.findByPage(page);
			List<Personal> lstPersonal = personalService.getPersonalBySegmentID(
					lstEmployee.get(0).getPk().getEmployee_Number(),
					lstEmployee.get(lstEmployee.size() - 1).getPk().getEmployee_Number());

			// merge two list

			Map<Long, IntergrationVacationDays> mergeLst = new HashMap<>();
			for (Personal iPersonal : lstPersonal) {
				mergeLst.put(iPersonal.getEmployee_ID(),
						new IntergrationVacationDays(iPersonal.getEmployee_ID(), iPersonal.getFirst_Name(),
								iPersonal.getLast_Name(), iPersonal.getGender(), iPersonal.getEthnicity(),
								iPersonal.getAddress1(), iPersonal.getAddress2(), iPersonal.getPhone_Number()));
			}

			for (Employee iEmployee : lstEmployee) {
				if (mergeLst.containsKey(Long.valueOf(iEmployee.getPk().getEmployee_Number())) == true) {
					mergeLst.get(Long.valueOf(iEmployee.getPk().getEmployee_Number()))
							.setVacationDays(iEmployee.getVacation_Days());
				}
			}
			return mergeLst;
		} else
			return null;
	}

	@RequestMapping(value = "benefit-plans", method = RequestMethod.GET)
	public Map<Long, IntergrationBeneftPlan> getBenefitPlan(Principal principal, @RequestParam("page") int page) {
		final int FUNCTION_ID = 3; // function_lợi ích kế hoạch
		Users u = userService.findByUserName(principal.getName()).get();
		// check acess control
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			Iterable<Personal> lstPersonal = personalService.findByPage(page);

			Map<Long, IntergrationBeneftPlan> resMap = new HashMap<>();
			for (Personal personal : lstPersonal) {
				IntergrationBeneftPlan item = new IntergrationBeneftPlan(personal.getEmployee_ID(),
						personal.getFirst_Name(), personal.getLast_Name(), personal.getGender(),
						personal.getEthnicity(), personal.getAddress1(), personal.getAddress2(),
						personal.getPhone_Number());
				if(personal.getBenefitPlan() != null) {
					item.setBenefitPlan(personal.getBenefitPlan().getPlan_Name(), personal.getBenefitPlan().getDeductable(), personal.getBenefitPlan().getPercentage_CoPay());
				}
				resMap.put(item.getEmployeeID(), item);
			}

			return resMap;
		} else {
			return null;
		}
	}

}
