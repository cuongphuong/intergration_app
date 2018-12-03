package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.custommodel.ItemInfomation;
import com.example.entitys1.Employee;
import com.example.entitys2.Personal;
import com.example.entitys3.AccessControlKey;
import com.example.entitys3.Users;
import com.example.service.ds1.EmployeeService;
import com.example.service.ds2.PersonalService;
import com.example.service.ds3.AccessControlService;
//import com.example.service.ds3.AccessControlService;
import com.example.service.ds3.UserService;

@Controller
@RestController
@RequestMapping("notification")
public class NotificationController {
	@Autowired
	private UserService userService;

	@Autowired
	private PersonalService personalService;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AccessControlService accessControlService;

	@RequestMapping(method = RequestMethod.GET)
	public Optional<Users> index(Principal principal) {
		Optional<Users> u = userService.findByUserName(principal.getName());
		return u;
	}

	// thong bao ky niem
	@RequestMapping(value = "ki-nien-td", method = RequestMethod.GET)
	public List<ItemInfomation> kiNienTuyenDung() {
		Calendar calendar;
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		System.out.println("check" + day + " - " + month + "-" + calendar.get(Calendar.YEAR));
		List<Personal> lstPersonal = personalService.kiNiemViecLam(day, month);

		List<ItemInfomation> lstTraVe = new ArrayList<>();
		for (Personal per : lstPersonal) {
			calendar.setTime(per.getEmployment().getHire_Date());
			int day2 = calendar.get(Calendar.DAY_OF_MONTH);
			int month2 = calendar.get(Calendar.MONTH) + 1;
			int year2 = calendar.get(Calendar.YEAR) + 1;

			String title = per.getFirst_Name() + " " + per.getLast_Name() + " kĩ niệm việc làm "
					+ String.valueOf(day - day2) + " ngày nữa.";
			String content = "Ngày vào làm của nhân viên " + per.getLast_Name() + " vào ngày " + day2 + "/" + month2
					+ "/" + year2;
			lstTraVe.add(new ItemInfomation(title, content));
		}

		return lstTraVe;
	}

	// get thong bao ngay nghi qua han
	@RequestMapping(value = "ngay-nghi-qua-han", method = RequestMethod.GET)
	public List<ItemInfomation> ngayNghiQuaHan(Principal principal) {
		final int FUNCTION_ID = 1; // function_tong_thu_nhap_nhan_vien
		Users u = userService.findByUserName(principal.getName()).get();
		int setQuaHan = 5;
		// check acess control
		if (accessControlService.checkAuthor(new AccessControlKey(FUNCTION_ID, u.getUserID())) == true) {
			List<Employee> lstEmployee = employeeService.getNgayNghiQuaHan(5);
			
			List<ItemInfomation> lstThongBao = new ArrayList<ItemInfomation>();
			for (Employee iEmployee : lstEmployee) {
				lstThongBao.add(new ItemInfomation(
						"Nhân viên " + iEmployee.getFirst_Name() + " " + iEmployee.getLast_Name() + " đã nghĩ quá "
								+ String.valueOf(iEmployee.getVacation_Days() - setQuaHan)
								+ " ngày so với ngày cho phép.",
						"Số ngày nghĩ của nhân viên là " + iEmployee.getVacation_Days() + " so với số ngày cho phép "
								+ setQuaHan + "."));
			}
			return lstThongBao;
		} else {
			return null;
		}
	}
}
