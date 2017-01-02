package org.shashank.library.controller;

import org.shashank.library.domain.Address;
import org.shashank.library.domain.Admin;
import org.shashank.library.domain.Login;
import org.shashank.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	public String Demo() {
		Admin admin = new Admin();
		admin.setFirstName("Shashank");
		admin.setLastName("Raghunath");
		admin.setEmployeeId(1);
		
		Address address = new Address();
		address.setStreet("E-3, 1608");
		address.setState("Mah");
		address.setCity("Kalyan");
		address.setPincode(421306);

		admin.setAddress(address);

		Login login = new Login();
		login.setUser(admin);
		login.setEmail("shashank@library.org");
		userService.addUser(admin, login);
		return "redirect:/";
	}
}
